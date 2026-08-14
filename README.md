<div align="center">
<img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=0,2,2,5,30&height=120&section=header&text=LeetCode%20Solutions&fontSize=34&fontColor=ffffff&fontAlignY=42&desc=Priyanshu%20Ghosh%20%7C%20Java%20%7C%20NeetCode%20150&descAlignY=65&descSize=14&descColor=79b8ff&animation=fadeIn" width="100%"/>
</div>

<br/>

<div align="center">

[![LeetCode](https://img.shields.io/badge/LeetCode-priyanshughosh30-FFA116?style=for-the-badge&logo=leetcode&logoColor=black)](https://leetcode.com/priyanshughosh30/)
[![Java](https://img.shields.io/badge/Language-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![NeetCode 150](https://img.shields.io/badge/List-NeetCode_150-1A56DB?style=for-the-badge)](https://neetcode.io/practice)

</div>

<br/>

<div align="center">

<img src="https://leetcard.jacoblin.cool/priyanshughosh30?theme=dark&font=Karma&ext=heatmap" alt="LeetCode Stats"/>

</div>

<br/>

<div align="center">

![LeetCode Stats](https://leetcode-stats-two-lyart.vercel.app/api?username=priyanshughosh30&theme=dark&background=0d1117&border=1A56DB&stroke=1A56DB&ring=79b8ff&fire=ff6b35&currStreakLabel=79b8ff&sideLabels=8fa3c0&dates=8fa3c0&currStreakNum=ffffff&sideNums=ffffff)

</div>

<br/>

## 📋 About

This repository tracks my progress through the **[NeetCode 150](https://neetcode.io/practice)** — a curated list of the most important DSA patterns for technical interviews. Every solution is written in **Java**, with a focus on writing clean, readable code and understanding the underlying pattern rather than just passing test cases.

Solutions are named directly after the problem (e.g. `1. Two Sum.java`) and live flat in the repo root for quick browsing and quick updates.

---

## 📁 Repository Structure

<!-- STRUCTURE_START -->
```
LeetCode-Solutions/
└── README.md
```
<!-- STRUCTURE_END -->

> This tree updates **automatically** — see below.

---

## 📝 Solution Format

Each file is self-contained and follows this pattern:

```java
/**
 * LeetCode #1 - Two Sum
 * https://leetcode.com/problems/two-sum/
 *
 * Pattern: Hashing
 * Time Complexity:  O(n)
 * Space Complexity: O(n)
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }
            seen.put(nums[i], i);
        }
        return new int[]{};
    }
}
```

---

## 📌 Problems Solved

<!-- PROBLEMS_TABLE_START -->
| # | Problem | Difficulty |
|---|---------|:---:|
<!-- PROBLEMS_TABLE_END -->

> This table updates **automatically** via GitHub Actions every time a new `.java` solution is pushed to `main`, using LeetCode's own search API to fetch the correct title, link, and difficulty — even if the filename has typos or shorthand.

---

## 🎯 NeetCode 150 Progress

<!-- PROGRESS_START -->
```
[░░░░░░░░░░░░░░░░░░░░]  0 / 150 solved
```
<!-- PROGRESS_END -->

<!-- PATTERN_TABLE_START -->
| Pattern | Status |
|:---|:---:|
<!-- PATTERN_TABLE_END -->

> Pattern status is auto-detected from each solved problem's official LeetCode topic tags — no manual editing needed.

---

## 🔗 Related

- 📄 [Data Structures and Algorithms: The Complete Masterclass](https://github.com/PG300604) — Udemy certification
- 💼 [Main GitHub Profile](https://github.com/PG300604)
- 🔗 [LinkedIn](https://linkedin.com/in/priyanshu-ghosh-)
- 🧩 [NeetCode 150 List](https://neetcode.io/practice)

<div align="center">
<img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=0,2,2,5,30&height=80&section=footer" width="100%"/>
</div>
