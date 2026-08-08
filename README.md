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
├── 1. Two Sum.java
├── 4.Median of Two Sorted Arrays.java
├── 11. Container With Most Water.java
├── 26.Remove Duplicates.java
├── 27. Remove Elements.java
├── 34. Find First and Last Position of Element in Sorted Array.java
├── 35.Search Index Position.java
├── 49. Group Anagrams.java
├── 66. Plus One.java
├── 121.Best Time To Buy and Sell Stock.java
├── 125.Valid Pallindrome.java
├── 128.Longest Consecutive Sequence.java
├── 169.Majority Element.java
├── 218. Contains Duplicate.java
├── 238.Product of Array Except Self.java
├── 242. Valid Anagram.java
├── 347.Top K Frequent Elements.java
├── 877.Stone Game.java
└── 3731.Find Missing Elements.java
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
| 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | 🟢 Easy |
| 4 | [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) | 🔴 Hard |
| 11 | [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) | 🟡 Medium |
| 26 | [Remove Duplicates](https://leetcode.com/problems/remove-duplicates/) | — |
| 27 | [Remove Elements](https://leetcode.com/problems/remove-elements/) | — |
| 34 | [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | 🟡 Medium |
| 35 | [Search Index Position](https://leetcode.com/problems/search-index-position/) | — |
| 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | 🟡 Medium |
| 66 | [Plus One](https://leetcode.com/problems/plus-one/) | 🟢 Easy |
| 121 | [Best Time To Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | 🟢 Easy |
| 125 | [Valid Pallindrome](https://leetcode.com/problems/valid-pallindrome/) | — |
| 128 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | 🟡 Medium |
| 169 | [Majority Element](https://leetcode.com/problems/majority-element/) | 🟢 Easy |
| 218 | [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) | 🟢 Easy |
| 238 | [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) | 🟡 Medium |
| 242 | [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | 🟢 Easy |
| 347 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | 🟡 Medium |
| 877 | [Stone Game](https://leetcode.com/problems/stone-game/) | 🟡 Medium |
| 3731 | [Find Missing Elements](https://leetcode.com/problems/find-missing-elements/) | 🟢 Easy |
<!-- PROBLEMS_TABLE_END -->

> This table updates **automatically** via GitHub Actions every time a new `.java` solution is pushed to `main`. No manual editing needed.

---

## 🎯 NeetCode 150 Progress

<!-- PROGRESS_START -->
```
[▓▓░░░░░░░░░░░░░░░░░░]  19 / 150 solved
```
<!-- PROGRESS_END -->

| Pattern | Status |
|:---|:---:|
| Arrays & Hashing | 🟢 In Progress |
| Two Pointers | 🟢 In Progress |
| Sliding Window | 🟢 In Progress |
| Stack | ⬜ Not Started |
| Binary Search | 🟢 In Progress |
| Linked List | ⬜ Not Started |
| Trees | ⬜ Not Started |
| Heap / Priority Queue | 🟢 In Progress |
| Backtracking | ⬜ Not Started |
| Graphs | ⬜ Not Started |
| Dynamic Programming | 🟢 In Progress |
| Greedy | ⬜ Not Started |
| Intervals | ⬜ Not Started |
| Math & Geometry | ⬜ Not Started |
| Bit Manipulation | ⬜ Not Started |

---

## 🔗 Related

- 📄 [Data Structures and Algorithms: The Complete Masterclass](https://github.com/PG300604) — Udemy certification
- 💼 [Main GitHub Profile](https://github.com/PG300604)
- 🔗 [LinkedIn](https://linkedin.com/in/priyanshu-ghosh-)
- 🧩 [NeetCode 150 List](https://neetcode.io/practice)

<div align="center">
<img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=0,2,2,5,30&height=80&section=footer" width="100%"/>
</div>
