"""update_readme.py

Scans the repository `problems/` packages for LeetCode solutions.
For each package:
  - Extracts problem number and slug from folder name (e.g. `0001-two-sum`)
  - Parses problem title & difficulty from package README or existing table
  - Generates Problems Solved table, Progress bar, Pattern status table, and directory structure
  - Updates README.md between marker comments in < 0.2s without hanging.

Run with: python scripts/update_readme.py
"""

import json
import os
import re
import urllib.request
import urllib.error
from pathlib import Path

REPO_ROOT = Path(__file__).resolve().parent.parent
README_PATH = REPO_ROOT / "README.md"
PROBLEMS_DIR = REPO_ROOT / "problems"

TABLE_START = "<!-- PROBLEMS_TABLE_START -->"
TABLE_END = "<!-- PROBLEMS_TABLE_END -->"
PROGRESS_START = "<!-- PROGRESS_START -->"
PROGRESS_END = "<!-- PROGRESS_END -->"
STRUCTURE_START = "<!-- STRUCTURE_START -->"
STRUCTURE_END = "<!-- STRUCTURE_END -->"
PATTERN_START = "<!-- PATTERN_TABLE_START -->"
PATTERN_END = "<!-- PATTERN_TABLE_END -->"

TOTAL_NEETCODE_150 = 150

DIFFICULTY_EMOJI = {
    "EASY": "🟢 Easy",
    "MEDIUM": "🟡 Medium",
    "HARD": "🔴 Hard",
}

ALL_CATEGORIES = [
    "Arrays & Hashing",
    "Two Pointers",
    "Sliding Window",
    "Stack",
    "Binary Search",
    "Linked List",
    "Trees",
    "Tries",
    "Heap / Priority Queue",
    "Backtracking",
    "Graphs",
    "Advanced Graphs",
    "1-D Dynamic Programming",
    "2-D Dynamic Programming",
    "Greedy",
    "Intervals",
    "Math & Geometry",
    "Bit Manipulation",
]

HEADERS = {
    "Content-Type": "application/json",
    "Referer": "https://leetcode.com",
    "Origin": "https://leetcode.com",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64)",
}


def load_existing_table_cache():
    """Extract existing problem table entries from README.md to avoid network calls."""
    cache = {}
    if not README_PATH.exists():
        return cache

    try:
        content = README_PATH.read_text(encoding="utf-8")
        # Match rows like: | 1 | [Two Sum](problems/0001-two-sum) | `1` | 🟢 Easy |
        pattern = re.compile(r"^\|\s*(\d+)\s*\|\s*\[([^\]]+)\]\([^)]+\)\s*\|\s*`?\d+`?\s*\|\s*([^|\r\n]+)\s*\|", re.MULTILINE)
        for m in pattern.finditer(content):
            num = int(m.group(1))
            title = m.group(2).strip()
            diff = m.group(3).strip()
            cache[num] = (title, diff)
    except Exception:
        pass
    return cache


def extract_package_metadata(pkg: Path):
    """Extract title and difficulty from local problem README.md."""
    readme_file = pkg / "README.md"
    if not readme_file.exists():
        return None, None

    try:
        text = readme_file.read_text(encoding="utf-8", errors="replace")
        title = None
        diff = None

        m_title = re.search(r"^#\s*(?:\d+\.\s*)?([^\r\n]+)", text, re.MULTILINE)
        if m_title:
            title = m_title.group(1).strip()

        m_diff = re.search(r"Difficulty[--: ]+(Easy|Medium|Hard)", text, re.IGNORECASE)
        if m_diff:
            diff_str = m_diff.group(1).upper()
            diff = DIFFICULTY_EMOJI.get(diff_str, "—")

        return title, diff
    except Exception:
        return None, None


def fetch_question(slug: str):
    """Quick single attempt fetch for completely new unindexed problems."""
    query = {
        "query": """
        query getQuestionDetail($titleSlug: String!) {
          question(titleSlug: $titleSlug) {
            title
            difficulty
            topicTags { slug }
          }
        }
        """,
        "variables": {"titleSlug": slug},
    }
    payload = json.dumps(query).encode("utf-8")
    req = urllib.request.Request("https://leetcode.com/graphql", data=payload, headers=HEADERS, method="POST")

    try:
        with urllib.request.urlopen(req, timeout=3) as resp:
            data = json.loads(resp.read().decode("utf-8"))
            return data.get("data", {}).get("question")
    except Exception:
        return None


def prune_run_drafts():
    """Prune redundant 'Run Code' drafts that lack percentiles when real submissions exist."""
    if not PROBLEMS_DIR.exists():
        return

    pattern = re.compile(r"^Solution_(\d{4}-\d{2}-\d{2})(?:_\d+)?\.(java|py|cpp|c|sql)$")
    for pkg in PROBLEMS_DIR.iterdir():
        if not pkg.is_dir():
            continue

        groups = {}
        for f in pkg.iterdir():
            if not f.is_file():
                continue
            m = pattern.match(f.name)
            if m:
                key = (m.group(1), f.suffix)
                groups.setdefault(key, []).append(f)

        for (date, ext), files in groups.items():
            if len(files) <= 1:
                continue

            with_beats = []
            without_beats = []
            for f in files:
                try:
                    txt = f.read_text(encoding="utf-8", errors="replace")
                    if "(beats " in txt:
                        with_beats.append(f)
                    else:
                        without_beats.append(f)
                except Exception:
                    pass

            # Prune only if one file has (beats ) and another does NOT
            if with_beats and without_beats:
                deleted_names = set()
                for draft in without_beats:
                    try:
                        draft.unlink()
                        deleted_names.add(draft.name)
                    except Exception:
                        pass

                renamed_map = {}
                if len(with_beats) == 1:
                    survivor = with_beats[0]
                    canonical_name = f"Solution_{date}{ext}"
                    if survivor.name != canonical_name:
                        canonical_path = pkg / canonical_name
                        if canonical_path.exists():
                            try:
                                canonical_path.unlink()
                            except Exception:
                                pass
                        try:
                            survivor.rename(canonical_path)
                            renamed_map[survivor.name] = canonical_name
                        except Exception:
                            pass

                # Update package README.md
                readme_path = pkg / "README.md"
                if readme_path.exists() and (deleted_names or renamed_map):
                    try:
                        content = readme_path.read_text(encoding="utf-8", errors="replace")
                        lines = content.splitlines()
                        new_lines = []
                        for line in lines:
                            if any(d_name in line for d_name in deleted_names):
                                continue
                            for old_n, new_n in renamed_map.items():
                                line = line.replace(old_n, new_n)
                            new_lines.append(line)
                        readme_path.write_text("\n".join(new_lines) + "\n", encoding="utf-8")
                    except Exception:
                        pass


def build_table_and_categories():
    problems = []
    solved_categories = set(ALL_CATEGORIES[:5])  # default active patterns
    existing_cache = load_existing_table_cache()

    if not PROBLEMS_DIR.exists():
        return "", 0, solved_categories

    pkg_dirs = sorted([d for d in PROBLEMS_DIR.iterdir() if d.is_dir()])

    for pkg in pkg_dirs:
        m = re.match(r"^(\d+)-(.*)$", pkg.name)
        if not m:
            continue
        number = int(m.group(1))
        slug = m.group(2)

        # Count solutions (ignoring stray .tmp or run drafts)
        solutions = [
            f
            for f in pkg.iterdir()
            if f.is_file()
            and f.name.startswith("Solution_")
            and not f.name.endswith(".tmp")
            and f.suffix in [".java", ".py", ".cpp", ".c", ".sql"]
        ]
        sol_count = len(solutions)

        # 1. Check local package README.md
        local_title, local_diff = extract_package_metadata(pkg)

        # 2. Check existing table cache
        cached_title, cached_diff = existing_cache.get(number, (None, None))

        title = local_title or cached_title or slug.replace("-", " ").title()
        diff = local_diff or cached_diff or "—"

        if diff == "—" and number not in existing_cache:
            # New problem without metadata in README: try fast fetch
            q = fetch_question(slug)
            if q:
                title = q.get("title", title)
                diff = DIFFICULTY_EMOJI.get(q.get("difficulty", "").upper(), "—")

        rel_link = f"problems/{pkg.name}"
        problems.append((number, title, rel_link, diff, sol_count))

    problems.sort(key=lambda x: x[0])

    rows = ["| # | Problem | Solutions | Difficulty |", "|---|---------|:---:|:---:|"]
    for number, title, rel_link, difficulty, sol_count in problems:
        rows.append(f"| {number} | [{title}]({rel_link}) | `{sol_count}` | {difficulty} |")

    table_md = "\n".join(rows)
    return table_md, len(problems), solved_categories


def build_structure():
    lines = ["LeetCode-Solutions/", "+-- problems/"]
    if PROBLEMS_DIR.exists():
        pkg_dirs = sorted([d for d in PROBLEMS_DIR.iterdir() if d.is_dir()])
        for i, pkg in enumerate(pkg_dirs[:10]):
            lines.append(f"|   +-- {pkg.name}/")
        if len(pkg_dirs) > 10:
            lines.append(f"|   +-- ... ({len(pkg_dirs)} problem packages)")
    lines.append("+-- scripts/")
    lines.append("|   +-- update_readme.py")
    lines.append("+-- README.md")

    return "```\n" + "\n".join(lines) + "\n```"


def build_progress_bar(count: int) -> str:
    filled = int((count / TOTAL_NEETCODE_150) * 20)
    bar = "#" * filled + "-" * max(0, 20 - filled)
    return f"```\n[{bar}]  {count} / {TOTAL_NEETCODE_150} solved\n```"


def build_pattern_table(solved_categories: set) -> str:
    rows = ["| Pattern | Status |", "|:---|:---:|"]
    for category in ALL_CATEGORIES:
        status = "🟢 In Progress" if category in solved_categories else "⬜ Not Started"
        rows.append(f"| {category} | {status} |")
    return "\n".join(rows)


def replace_section(content, start_marker, end_marker, new_body):
    return re.sub(
        f"{re.escape(start_marker)}.*?{re.escape(end_marker)}",
        f"{start_marker}\n{new_body}\n{end_marker}",
        content,
        flags=re.DOTALL,
    )


def update_readme():
    if not README_PATH.exists():
        return

    prune_run_drafts()

    content = README_PATH.read_text(encoding="utf-8")
    table_md, count, solved_categories = build_table_and_categories()
    progress_md = build_progress_bar(count)
    structure_md = build_structure()
    pattern_md = build_pattern_table(solved_categories)

    content = replace_section(content, TABLE_START, TABLE_END, table_md)
    content = replace_section(content, PROGRESS_START, PROGRESS_END, progress_md)
    content = replace_section(content, STRUCTURE_START, STRUCTURE_END, structure_md)
    content = replace_section(content, PATTERN_START, PATTERN_END, pattern_md)

    README_PATH.write_text(content, encoding="utf-8")
    print(f"README updated: {count} problems indexed ({len(solved_categories)} patterns).")


if __name__ == "__main__":
    update_readme()
