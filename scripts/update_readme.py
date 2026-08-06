"""
update_readme.py

Scans the repo root for LeetCode solution files named like:
    "1. Two Sum.java"
    "218. Contains Duplicate.java"

For each file:
  - Extracts the problem number and title
  - Builds a LeetCode URL from the title (slugified)
  - Fetches difficulty from LeetCode's public GraphQL API
  - Regenerates the "Problems Solved" table and progress bar
    inside README.md, between marker comments.

Run with: python scripts/update_readme.py
"""

import os
import re
import glob
import requests

README_PATH = "README.md"
TABLE_START = "<!-- PROBLEMS_TABLE_START -->"
TABLE_END = "<!-- PROBLEMS_TABLE_END -->"
PROGRESS_START = "<!-- PROGRESS_START -->"
PROGRESS_END = "<!-- PROGRESS_END -->"

TOTAL_NEETCODE_150 = 150

DIFFICULTY_EMOJI = {
    "Easy": "🟢 Easy",
    "Medium": "🟡 Medium",
    "Hard": "🔴 Hard",
}


def slugify(title: str) -> str:
    """Convert 'Two Sum' -> 'two-sum' (LeetCode's URL slug format)."""
    slug = title.lower().strip()
    slug = re.sub(r"[^a-z0-9\s-]", "", slug)
    slug = re.sub(r"\s+", "-", slug)
    return slug


def parse_filename(filename: str):
    """
    Parses '1. Two Sum.java' or '218.Contains Duplicate.java'
    into (number, title).
    """
    name = filename.replace(".java", "")
    match = re.match(r"^(\d+)\.\s*(.+)$", name)
    if not match:
        return None, None
    number, title = match.groups()
    return number, title.strip()


def fetch_difficulty(slug: str) -> str:
    """Query LeetCode's public GraphQL API for problem difficulty."""
    query = {
        "query": """
        query getQuestionDetail($titleSlug: String!) {
          question(titleSlug: $titleSlug) {
            difficulty
          }
        }
        """,
        "variables": {"titleSlug": slug},
    }
    try:
        res = requests.post(
            "https://leetcode.com/graphql",
            json=query,
            headers={"Content-Type": "application/json"},
            timeout=10,
        )
        data = res.json()
        difficulty = data["data"]["question"]["difficulty"]
        return DIFFICULTY_EMOJI.get(difficulty, difficulty)
    except Exception:
        return "—"  # Fallback if API fails or problem not found


def build_table():
    files = glob.glob("*.java")
    problems = []

    for f in files:
        number, title = parse_filename(f)
        if not number:
            continue
        slug = slugify(title)
        url = f"https://leetcode.com/problems/{slug}/"
        difficulty = fetch_difficulty(slug)
        problems.append((int(number), title, url, difficulty))

    problems.sort(key=lambda x: x[0])

    rows = ["| # | Problem | Difficulty |", "|---|---------|:---:|"]
    for number, title, url, difficulty in problems:
        rows.append(f"| {number} | [{title}]({url}) | {difficulty} |")

    table_md = "\n".join(rows)
    count = len(problems)
    return table_md, count


def build_progress_bar(count: int) -> str:
    filled = int((count / TOTAL_NEETCODE_150) * 20)
    bar = "▓" * filled + "░" * (20 - filled)
    return f"```\n[{bar}]  {count} / {TOTAL_NEETCODE_150} solved\n```"


def update_readme():
    with open(README_PATH, "r", encoding="utf-8") as f:
        content = f.read()

    table_md, count = build_table()
    progress_md = build_progress_bar(count)

    # Replace problems table
    content = re.sub(
        f"{re.escape(TABLE_START)}.*?{re.escape(TABLE_END)}",
        f"{TABLE_START}\n{table_md}\n{TABLE_END}",
        content,
        flags=re.DOTALL,
    )

    # Replace progress bar
    content = re.sub(
        f"{re.escape(PROGRESS_START)}.*?{re.escape(PROGRESS_END)}",
        f"{PROGRESS_START}\n{progress_md}\n{PROGRESS_END}",
        content,
        flags=re.DOTALL,
    )

    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write(content)

    print(f"README updated — {count} problems found.")


if __name__ == "__main__":
    update_readme()
