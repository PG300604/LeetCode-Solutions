"""update_readme.py

Scans the repository `problems/` packages for LeetCode solutions.
For each package:
  - Extracts problem number and slug from folder name (e.g. `0001-two-sum`)
  - Queries LeetCode GraphQL endpoint using standard library urllib
  - Generates Problems Solved table, Progress bar, Pattern status table, and directory structure
  - Updates README.md between marker comments.

Run with: python scripts/update_readme.py
"""

import json
import os
import re
import time
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

CATEGORY_RULES = [
    ("Two Pointers", {"two-pointers"}),
    ("Sliding Window", {"sliding-window"}),
    ("Stack", {"stack", "monotonic-stack"}),
    ("Binary Search", {"binary-search"}),
    ("Linked List", {"linked-list", "doubly-linked-list"}),
    ("Trees", {"tree", "binary-tree", "binary-search-tree"}),
    ("Tries", {"trie"}),
    ("Heap / Priority Queue", {"heap-priority-queue"}),
    ("Backtracking", {"backtracking"}),
    ("Graphs", {"graph", "breadth-first-search", "depth-first-search", "union-find"}),
    ("Advanced Graphs", {"topological-sort", "shortest-path", "minimum-spanning-tree"}),
    ("1-D Dynamic Programming", {"dynamic-programming", "memoization"}),
    ("2-D Dynamic Programming", {"matrix", "multidimensional-dynamic-programming"}),
    ("Greedy", {"greedy"}),
    ("Intervals", {"intervals", "line-sweep"}),
    ("Math & Geometry", {"math", "geometry", "number-theory"}),
    ("Bit Manipulation", {"bit-manipulation"}),
    ("Arrays & Hashing", {"array", "hash-table", "string", "sorting"}),
]

HEADERS = {
    "Content-Type": "application/json",
    "Referer": "https://leetcode.com",
    "Origin": "https://leetcode.com",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0 Safari/537.36",
}


def fetch_question(slug: str):
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

    for attempt in range(2):
        try:
            with urllib.request.urlopen(req, timeout=10) as resp:
                data = json.loads(resp.read().decode("utf-8"))
                return data.get("data", {}).get("question")
        except Exception:
            time.sleep(0.5)
    return None


def categorize(topic_tags) -> str:
    slugs = {t["slug"] for t in topic_tags}
    for category, tag_list in CATEGORY_RULES:
        if slugs.intersection(tag_list):
            return category
    return "Arrays & Hashing"


def build_table_and_categories():
    problems = []
    solved_categories = set()

    if not PROBLEMS_DIR.exists():
        return "", 0, set()

    pkg_dirs = sorted([d for d in PROBLEMS_DIR.iterdir() if d.is_dir()])

    for pkg in pkg_dirs:
        m = re.match(r"^(\d+)-(.*)$", pkg.name)
        if not m:
            continue
        number = int(m.group(1))
        slug = m.group(2)

        # Count solutions
        solutions = [f for f in pkg.iterdir() if f.is_file() and f.suffix in [".java", ".py", ".cpp", ".c", ".sql"]]
        sol_count = len(solutions)

        # Title from package name
        clean_title = slug.replace("-", " ").title()

        question = fetch_question(slug)
        if question:
            clean_title = question["title"]
            diff = DIFFICULTY_EMOJI.get(question["difficulty"].upper(), "—")
            cat = categorize(question.get("topicTags", []))
            solved_categories.add(cat)
        else:
            diff = "—"

        rel_link = f"problems/{pkg.name}"
        problems.append((number, clean_title, rel_link, diff, sol_count))

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
    print(f"\nREADME updated — {count} problems indexed, {len(solved_categories)} patterns touched.")


if __name__ == "__main__":
    update_readme()
