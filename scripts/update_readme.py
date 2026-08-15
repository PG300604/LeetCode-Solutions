"""
update_readme.py

Scans the repo root for LeetCode solution files named like:
    "1. Two Sum.java"
    "218. Contains Duplicate.java"

For each file:
  - Extracts the problem number and title from the filename
  - Resolves the correct LeetCode URL slug using a manual override table
    (falls back to a best-guess slug if not in the table)
  - Queries LeetCode's STABLE single-question GraphQL endpoint (much more
    reliable than the search/list endpoint, which frequently blocks
    automated requests or changes schema without notice)
  - Pulls difficulty + topic tags, then regenerates:
        1. Problems Solved table
        2. Progress bar
        3. NeetCode-pattern status table (auto-detected from topic tags)
        4. Repository structure tree
    inside README.md, each between its own marker comments.

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

HEADERS = {
    "Content-Type": "application/json",
    "Referer": "https://leetcode.com",
    "Origin": "https://leetcode.com",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                  "(KHTML, like Gecko) Chrome/124.0 Safari/537.36",
}

# Manual overrides: normalized filename title -> correct LeetCode slug.
# This avoids depending on LeetCode's fragile fuzzy-search endpoint —
# lookups go straight to the stable single-question query instead.
# Add new entries here whenever a filename doesn't exactly match the
# official LeetCode title.
SLUG_OVERRIDES = {
    "two sum": "two-sum",
    "median of two sorted arrays": "median-of-two-sorted-arrays",
    "container with most water": "container-with-most-water",
    "3sum": "3sum",
    "remove duplicates": "remove-duplicates-from-sorted-array",
    "remove elements": "remove-element",
    "trapping rain water": "trapping-rain-water",
    "find first and last position of element in sorted array": "find-first-and-last-position-of-element-in-sorted-array",
    "search index position": "search-insert-position",
    "group anagrams": "group-anagrams",
    "plus one": "plus-one",
    "best time to buy and sell stock": "best-time-to-buy-and-sell-stock",
    "valid pallindrome": "valid-palindrome",
    "valid palindrome": "valid-palindrome",
    "longest consecutive sequence": "longest-consecutive-sequence",
    "two sum ii - input array is sorted": "two-sum-ii-input-array-is-sorted",
    "majority element": "majority-element",
    "contains duplicate": "contains-duplicate",
    "contains duplicate ii": "contains-duplicate-ii",
    "product of array except self": "product-of-array-except-self",
    "valid anagram": "valid-anagram",
    "top k frequent elements": "top-k-frequent-elements",
    "maximum average subarray": "maximum-average-subarray-i",
    "stone game": "stone-game",
}

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


def naive_slug(title: str) -> str:
    slug = title.lower().strip()
    slug = re.sub(r"[^a-z0-9\s-]", "", slug)
    slug = re.sub(r"\s+", "-", slug)
    slug = re.sub(r"-+", "-", slug)
    return slug


def resolve_slug(title: str) -> str:
    key = title.lower().strip()
    if key in SLUG_OVERRIDES:
        return SLUG_OVERRIDES[key]
    return naive_slug(title)


def fetch_question(slug: str):
    """
    Queries LeetCode's stable single-question endpoint (question(titleSlug: ...)).
    This endpoint is far more reliable for automated/CI use than the
    search or list endpoints, which frequently block bots or change shape.
    """
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

    for attempt in range(2):
        try:
            res = requests.post(
                "https://leetcode.com/graphql",
                json=query,
                headers=HEADERS,
                timeout=15,
            )
            if res.status_code != 200:
                print(f"  [warn] slug='{slug}' HTTP {res.status_code} on attempt {attempt+1}")
                time.sleep(1)
                continue

            data = res.json()
            question = data.get("data", {}).get("question")
            if question is None:
                print(f"  [warn] slug='{slug}' returned no question (bad slug?)")
                return None
            return question
        except Exception as e:
            print(f"  [warn] slug='{slug}' request failed: {e}")
            time.sleep(1)

    return None


def categorize(topic_tags) -> str:
    slugs = {t["slug"] for t in topic_tags}
    for category, tag_list in CATEGORY_RULES:
        if slugs.intersection(tag_list):
            return category
    return "Arrays & Hashing"


def build_table_and_categories():
    files = glob.glob("*.java")
    problems = []
    solved_categories = set()

    for f in sorted(files):
        number, filename_title = parse_filename(f)
        if not number:
            continue

        slug = resolve_slug(filename_title)
        print(f"Resolving #{number} '{filename_title}' -> slug '{slug}'")
        question = fetch_question(slug)
        time.sleep(0.4)  # be polite to LeetCode's API

        if question:
            official_title = question["title"]
            difficulty = DIFFICULTY_EMOJI.get(question["difficulty"].upper(), "—")
            category = categorize(question.get("topicTags", []))
            solved_categories.add(category)
        else:
            official_title = filename_title
            difficulty = "—"

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
        connector = "+-- " if is_last else "|-- "
        lines.append(f"{connector}{filename}")
    lines.append("+-- README.md")

    return "```\n" + "\n".join(lines) + "\n```"


def build_progress_bar(count: int) -> str:
    # Plain ASCII bar — avoids unicode block characters mangling on some
    # terminals/encodings (this replaced an earlier unicode-based bar).
    filled = int((count / TOTAL_NEETCODE_150) * 20)
    bar = "#" * filled + "-" * (20 - filled)
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

    print(f"\nREADME updated — {count} problems found, {len(solved_categories)} patterns touched.")


if __name__ == "__main__":
    update_readme()
