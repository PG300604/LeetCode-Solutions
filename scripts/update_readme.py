"""
update_readme.py

Scans the repo root for LeetCode solution files named like:
    "1. Two Sum.java"
    "218. Contains Duplicate.java"

For each file:
  - Extracts the problem number and title from the filename
  - Uses LeetCode's own search API to find the best-matching problem
    (robust to typos/abbreviations in the filename — e.g. "Pallindrome",
    "Remove Elements" vs "Remove Element", "Search Index Position" vs
    "Search Insert Position")
  - Pulls the OFFICIAL title, correct URL slug, difficulty, and topic tags
  - Regenerates three sections inside README.md, each between its own
    marker comments:
        1. Problems Solved table
        2. Progress bar
        3. NeetCode-pattern status table (auto-detected from topic tags)

Run with: python scripts/update_readme.py
"""

import os
import re
import glob
import time
import requests

README_PATH = "README.md"

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

# Ordered list of (Category label, [topic tag slugs that count as this category])
# A problem is grouped into the FIRST category whose tag it matches.
CATEGORY_RULES = [
    ("Two Pointers",          ["two-pointers"]),
    ("Sliding Window",        ["sliding-window"]),
    ("Stack",                 ["stack", "monotonic-stack"]),
    ("Binary Search",         ["binary-search"]),
    ("Linked List",           ["linked-list"]),
    ("Trees",                 ["tree", "binary-tree", "binary-search-tree"]),
    ("Heap / Priority Queue", ["heap-priority-queue"]),
    ("Backtracking",          ["backtracking"]),
    ("Graphs",                ["graph", "union-find", "topological-sort"]),
    ("Dynamic Programming",   ["dynamic-programming"]),
    ("Greedy",                ["greedy"]),
    ("Intervals",             ["interval"]),
    ("Bit Manipulation",      ["bit-manipulation"]),
    ("Math & Geometry",       ["math", "geometry"]),
    ("Arrays & Hashing",      ["array", "hash-table", "string"]),  # catch-all, keep last
]
ALL_CATEGORIES = [c for c, _ in CATEGORY_RULES]


def parse_filename(filename: str):
    """Parses '1. Two Sum.java' or '218.Contains Duplicate.java' -> (number, title)."""
    name = filename.replace(".java", "")
    match = re.match(r"^(\d+)\.\s*(.+)$", name)
    if not match:
        return None, None
    number, title = match.groups()
    return number, title.strip()


def search_leetcode(title: str):
    """
    Uses LeetCode's own fuzzy search (problemsetQuestionList) to find the
    best-matching problem for a given (possibly slightly wrong) title.
    Returns dict with title, titleSlug, difficulty, topicTags — or None.
    """
    query = {
        "query": """
        query problemsetQuestionList($categorySlug: String, $limit: Int, $skip: Int, $filters: QuestionListFilterInput) {
          questionList: problemsetQuestionListV2(
            categorySlug: $categorySlug
            limit: $limit
            skip: $skip
            filters: $filters
          ) {
            questions {
              title
              titleSlug
              difficulty
              topicTags { slug }
            }
          }
        }
        """,
        "variables": {
            "categorySlug": "",
            "limit": 1,
            "skip": 0,
            "filters": {"searchKeywords": title},
        },
    }
    try:
        res = requests.post(
            "https://leetcode.com/graphql",
            json=query,
            headers={"Content-Type": "application/json", "Referer": "https://leetcode.com"},
            timeout=15,
        )
        data = res.json()
        questions = data["data"]["questionList"]["questions"]
        if not questions:
            return None
        return questions[0]
    except Exception:
        return None


def categorize(topic_tags) -> str:
    slugs = {t["slug"] for t in topic_tags}
    for category, tag_list in CATEGORY_RULES:
        if slugs.intersection(tag_list):
            return category
    return "Arrays & Hashing"  # safe default


def build_table_and_categories():
    files = glob.glob("*.java")
    problems = []
    solved_categories = set()

    for f in files:
        number, filename_title = parse_filename(f)
        if not number:
            continue

        result = search_leetcode(filename_title)
        time.sleep(0.3)  # be polite to LeetCode's API

        if result:
            official_title = result["title"]
            slug = result["titleSlug"]
            difficulty = DIFFICULTY_EMOJI.get(result["difficulty"].upper(), "—")
            category = categorize(result.get("topicTags", []))
            solved_categories.add(category)
        else:
            # Fallback: naive slug guess if search fails entirely
            official_title = filename_title
            slug = re.sub(r"[^a-z0-9\s-]", "", filename_title.lower())
            slug = re.sub(r"\s+", "-", slug.strip())
            difficulty = "—"
            category = None

        url = f"https://leetcode.com/problems/{slug}/"
        problems.append((int(number), official_title, url, difficulty))

    problems.sort(key=lambda x: x[0])

    rows = ["| # | Problem | Difficulty |", "|---|---------|:---:|"]
    for number, title, url, difficulty in problems:
        rows.append(f"| {number} | [{title}]({url}) | {difficulty} |")

    table_md = "\n".join(rows)
    return table_md, len(problems), solved_categories


def build_structure():
    files = glob.glob("*.java")
    parsed = []
    for f in files:
        number, _ = parse_filename(f)
        if number:
            parsed.append((int(number), f))
    parsed.sort(key=lambda x: x[0])

    lines = ["LeetCode-Solutions/"]
    for i, (_, filename) in enumerate(parsed):
        is_last = i == len(parsed) - 1
        connector = "└──" if is_last else "├──"
        lines.append(f"{connector} {filename}")
    lines.append("└── README.md")

    return "```\n" + "\n".join(lines) + "\n```"


def build_progress_bar(count: int) -> str:
    filled = int((count / TOTAL_NEETCODE_150) * 20)
    bar = "▓" * filled + "░" * (20 - filled)
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
    with open(README_PATH, "r", encoding="utf-8") as f:
        content = f.read()

    table_md, count, solved_categories = build_table_and_categories()
    progress_md = build_progress_bar(count)
    structure_md = build_structure()
    pattern_md = build_pattern_table(solved_categories)

    content = replace_section(content, TABLE_START, TABLE_END, table_md)
    content = replace_section(content, PROGRESS_START, PROGRESS_END, progress_md)
    content = replace_section(content, STRUCTURE_START, STRUCTURE_END, structure_md)
    content = replace_section(content, PATTERN_START, PATTERN_END, pattern_md)

    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write(content)

    print(f"README updated — {count} problems found, {len(solved_categories)} patterns touched.")


if __name__ == "__main__":
    update_readme()
