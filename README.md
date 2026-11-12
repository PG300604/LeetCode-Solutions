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

<img src="https://leetcard.jacoblin.cool/priyanshughosh30?theme=dark&font=Karma&ext=activity" alt="LeetCode Activity"/>

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
+-- problems/
|   +-- 0001-two-sum/
|   +-- 0004-median-of-two-sorted-arrays/
|   +-- 0007-reverse-integer/
|   +-- 0011-container-with-most-water/
|   +-- 0014-longest-common-prefix/
|   +-- 0015-3sum/
|   +-- 0020-valid-parentheses/
|   +-- 0022-generate-parentheses/
|   +-- 0026-remove-duplicates/
|   +-- 0027-remove-elements/
|   +-- ... (128 problem packages)
+-- scripts/
|   +-- update_readme.py
+-- README.md
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
| # | Problem | Solutions | Difficulty |
|---|---------|:---:|:---:|
| 1 | [Two Sum](problems/0001-two-sum) | `1` | 🟢 Easy |
| 4 | [Median of Two Sorted Arrays](problems/0004-median-of-two-sorted-arrays) | `1` | 🔴 Hard |
| 7 | [Reverse Integer](problems/0007-reverse-integer) | `1` | 🟡 Medium |
| 11 | [Container With Most Water](problems/0011-container-with-most-water) | `1` | 🟡 Medium |
| 14 | [Longest Common Prefix](problems/0014-longest-common-prefix) | `1` | 🟢 Easy |
| 15 | [3Sum](problems/0015-3sum) | `1` | 🟡 Medium |
| 20 | [Valid Parentheses(with stack)](problems/0020-valid-parentheses) | `2` | 🟢 Easy |
| 22 | [Generate Parentheses](problems/0022-generate-parentheses) | `1` | 🟡 Medium |
| 26 | [Remove Duplicates](problems/0026-remove-duplicates) | `1` | — |
| 27 | [Remove Elements](problems/0027-remove-elements) | `1` | — |
| 28 | [Find the Index of the First Occurrence in a String](problems/0028-find-the-index-of-the-first-occurrence-in-a-string) | `1` | 🟢 Easy |
| 32 | [Longest Valid Parentheses](problems/0032-longest-valid-parentheses) | `2` | 🔴 Hard |
| 34 | [Find First and Last Position of Element in Sorted Array](problems/0034-find-first-and-last-position-of-element-in-sorted-array) | `1` | 🟡 Medium |
| 35 | [Search Index Position](problems/0035-search-index-position) | `1` | — |
| 49 | [Group Anagrams](problems/0049-group-anagrams) | `1` | 🟡 Medium |
| 58 | [Length of Last Word](problems/0058-length-of-last-word) | `1` | 🟢 Easy |
| 66 | [Plus One](problems/0066-plus-one) | `1` | 🟢 Easy |
| 74 | [Search in 2D Matrix](problems/0074-search-in-2d-matrix) | `1` | — |
| 80 | [Remove Duplicates from Sorted Array II](problems/0080-remove-duplicates-from-sorted-array-ii) | `1` | 🟡 Medium |
| 88 | [Merge Sorted Array](problems/0088-merge-sorted-array) | `1` | 🟢 Easy |
| 121 | [Best Time To Buy and Sell Stock](problems/0121-best-time-to-buy-and-sell-stock) | `1` | 🟢 Easy |
| 122 | [Best Time to Buy and Sell Stock II](problems/0122-best-time-to-buy-and-sell-stock-ii) | `1` | 🟡 Medium |
| 125 | [Valid Pallindrome](problems/0125-valid-pallindrome) | `1` | — |
| 128 | [Longest Consecutive Sequence](problems/0128-longest-consecutive-sequence) | `1` | 🟡 Medium |
| 151 | [Reverse Words in a String](problems/0151-reverse-words-in-a-string) | `1` | 🟡 Medium |
| 167 | [Two Sum II - Input Array is Sorted](problems/0167-two-sum-ii-input-array-is-sorted) | `1` | 🟡 Medium |
| 169 | [Majority Element](problems/0169-majority-element) | `1` | 🟢 Easy |
| 189 | [Rotate Array](problems/0189-rotate-array) | `1` | 🟡 Medium |
| 218 | [Contains Duplicate](problems/0218-contains-duplicate) | `1` | 🟢 Easy |
| 219 | [Contains Duplicate II](problems/0219-contains-duplicate-ii) | `1` | 🟢 Easy |
| 223 | [Rectangle Area](problems/0223-rectangle-area) | `1` | 🟡 Medium |
| 227 | [Basic Calculator II](problems/0227-basic-calculator-ii) | `1` | 🟡 Medium |
| 229 | [Majority Element II](problems/0229-majority-element-ii) | `1` | 🟡 Medium |
| 238 | [Product of Array Except Self](problems/0238-product-of-array-except-self) | `1` | 🟡 Medium |
| 242 | [Valid Anagram](problems/0242-valid-anagram) | `1` | 🟢 Easy |
| 268 | [Missing Number](problems/0268-missing-number) | `1` | 🟢 Easy |
| 283 | [Move Zeroes](problems/0283-move-zeroes) | `1` | 🟢 Easy |
| 301 | [Remove Invalid Parentheses](problems/0301-remove-invalid-parentheses) | `1` | 🔴 Hard |
| 316 | [Remove Duplicate Letters](problems/0316-remove-duplicate-letters) | `1` | 🟡 Medium |
| 334 | [Increasing Triplet Subsequence](problems/0334-increasing-triplet-subsequence) | `1` | 🟡 Medium |
| 345 | [Reverse Vowels of a String](problems/0345-reverse-vowels-of-a-string) | `1` | 🟢 Easy |
| 347 | [Top K Frequent Elements](problems/0347-top-k-frequent-elements) | `1` | 🟡 Medium |
| 349 | [Intersection of Two Arrays](problems/0349-intersection-of-two-arrays) | `1` | 🟢 Easy |
| 350 | [Intersection of Two Arrays II](problems/0350-intersection-of-two-arrays-ii) | `1` | 🟢 Easy |
| 371 | [Sum of Two Integers](problems/0371-sum-of-two-integers) | `1` | 🟡 Medium |
| 392 | [Is Subsequence](problems/0392-is-subsequence) | `1` | 🟢 Easy |
| 402 | [Remove K Digits](problems/0402-remove-k-digits) | `1` | 🟡 Medium |
| 443 | [String Compression](problems/0443-string-compression) | `1` | 🟡 Medium |
| 448 | [Find All Numbers Disappeared in an Array](problems/0448-find-all-numbers-disappeared-in-an-array) | `1` | 🟢 Easy |
| 453 | [Minimum Moves to Equal Array Elements](problems/0453-minimum-moves-to-equal-array-elements) | `1` | 🟡 Medium |
| 462 | [Minimum Moves to Equal Array Elements II](problems/0462-minimum-moves-to-equal-array-elements-ii) | `1` | 🟡 Medium |
| 485 | [Max Consecutive Ones](problems/0485-max-consecutive-ones) | `1` | 🟢 Easy |
| 605 | [Can Place Flowers](problems/0605-can-place-flowers) | `1` | 🟢 Easy |
| 643 | [Maximum Average Subarray](problems/0643-maximum-average-subarray) | `1` | — |
| 678 | [Valid Parenthesis String](problems/0678-valid-parenthesis-string) | `1` | 🟡 Medium |
| 724 | [Find Pivot Index](problems/0724-find-pivot-index) | `1` | 🟢 Easy |
| 739 | [Daily Temperatures](problems/0739-daily-temperatures) | `1` | 🟡 Medium |
| 771 | [Jewels and Stones](problems/0771-jewels-and-stones) | `1` | 🟢 Easy |
| 856 | [Score of Parentheses](problems/0856-score-of-parentheses) | `1` | 🟡 Medium |
| 877 | [Stone Game](problems/0877-stone-game) | `1` | 🟡 Medium |
| 921 | [Minimum Add to Make Parentheses Valid](problems/0921-minimum-add-to-make-parentheses-valid) | `1` | 🟡 Medium |
| 1004 | [Max Consecutive Ones III](problems/1004-max-consecutive-ones-iii) | `1` | 🟡 Medium |
| 1021 | [Remove Outermost Parentheses](problems/1021-remove-outermost-parentheses) | `1` | 🟢 Easy |
| 1071 | [Greatest Common Divisor of Strings](problems/1071-greatest-common-divisor-of-strings) | `1` | 🟢 Easy |
| 1081 | [Smallest Subsequence of Distinct Characters](problems/1081-smallest-subsequence-of-distinct-characters) | `1` | 🟡 Medium |
| 1207 | [Unique Number of Occurrences](problems/1207-unique-number-of-occurrences) | `1` | 🟢 Easy |
| 1254 | [Number of Closed Islands](problems/1254-number-of-closed-islands) | `1` | 🟡 Medium |
| 1267 | [Count Servers that Communicate](problems/1267-count-servers-that-communicate) | `1` | 🟡 Medium |
| 1385 | [Find the Distance Value Between Two Arrays](problems/1385-find-the-distance-value-between-two-arrays) | `2` | 🟢 Easy |
| 1431 | [Kids With the Greatest Number of Candies](problems/1431-kids-with-the-greatest-number-of-candies) | `1` | 🟢 Easy |
| 1456 | [Maximum Number of Vowels in a Substring of Given Length](problems/1456-maximum-number-of-vowels-in-a-substring-of-given-length) | `1` | 🟡 Medium |
| 1493 | [Longest Subarray of 1's After Deleting One Element](problems/1493-longest-subarray-of-1s-after-deleting-one-element) | `1` | 🟡 Medium |
| 1541 | [Minimum Insertions to Balance a Parentheses String](problems/1541-minimum-insertions-to-balance-a-parentheses-string) | `1` | 🟡 Medium |
| 1569 | [Number of Ways to Reorder Array to Get Same BST](problems/1569-number-of-ways-to-reorder-array-to-get-same-bst) | `1` | 🔴 Hard |
| 1606 | [Find Servers That Handled Most Number of Requests](problems/1606-find-servers-that-handled-most-number-of-requests) | `1` | 🔴 Hard |
| 1657 | [Determine if Two Strings Are Close](problems/1657-determine-if-two-strings-are-close) | `1` | 🟡 Medium |
| 1679 | [Max Number of K-Sum Pairs](problems/1679-max-number-of-k-sum-pairs) | `1` | 🟡 Medium |
| 1748 | [Sum of Unique Elements](problems/1748-sum-of-unique-elements) | `1` | 🟢 Easy |
| 1768 | [Merge Strings Alternately](problems/1768-merge-strings-alternately) | `1` | 🟢 Easy |
| 1882 | [Process Tasks Using Servers](problems/1882-process-tasks-using-servers) | `2` | 🟡 Medium |
| 1929 | [Concatenation of Array](problems/1929-concatenation-of-array) | `1` | 🟢 Easy |
| 2089 | [Find Target Indices After Sorting Array](problems/2089-find-target-indices-after-sorting-array) | `1` | 🟢 Easy |
| 2215 | [Find the Difference of Two Arrays](problems/2215-find-the-difference-of-two-arrays) | `1` | 🟢 Easy |
| 2235 | [Add Two Integers](problems/2235-add-two-integers) | `1` | 🟢 Easy |
| 2333 | [Minimum Sum of Squared Difference](problems/2333-minimum-sum-of-squared-difference) | `1` | 🟡 Medium |
| 2352 | [Equal Row and Column Pairs](problems/2352-equal-row-and-column-pairs) | `1` | 🟡 Medium |
| 2390 | [Removing Stars From a String](problems/2390-removing-stars-from-a-string) | `1` | 🟡 Medium |
| 2409 | [Count Days Spent Together](problems/2409-count-days-spent-together) | `1` | 🟢 Easy |
| 2747 | [Count Zero Request Servers](problems/2747-count-zero-request-servers) | `2` | 🟡 Medium |
| 2769 | [Find the Maximum Achievable Number](problems/2769-find-the-maximum-achievable-number) | `1` | 🟢 Easy |
| 2778 | [Sum of Squares of Special Elements](problems/2778-sum-of-squares-of-special-elements) | `1` | 🟢 Easy |
| 2913 | [Subarrays Distinct Element Sum of Squares I](problems/2913-subarrays-distinct-element-sum-of-squares-i) | `1` | 🟢 Easy |
| 2916 | [Subarrays Distinct Element Sum of Squares II](problems/2916-subarrays-distinct-element-sum-of-squares-ii) | `1` | 🔴 Hard |
| 3105 | [Longest Strictly Increasing or Strictly Decreasing Subarray](problems/3105-longest-strictly-increasing-or-strictly-decreasing-subarray) | `2` | 🟢 Easy |
| 3505 | [Minimum Operations to Make Elements Within K Subarrays Equal](problems/3505-minimum-operations-to-make-elements-within-k-subarrays-equal) | `1` | 🔴 Hard |
| 3551 | [Minimum Swaps to Sort by Digit Sum](problems/3551-minimum-swaps-to-sort-by-digit-sum) | `1` | 🟡 Medium |
| 3619 | [Count Islands With Total Value Divisible by K](problems/3619-count-islands-with-total-value-divisible-by-k) | `1` | 🟡 Medium |
| 3644 | [Maximum K to Sort a Permutation](problems/3644-maximum-k-to-sort-a-permutation) | `1` | 🟡 Medium |
| 3665 | [Twisted Mirror Path Count](problems/3665-twisted-mirror-path-count) | `1` | 🟡 Medium |
| 3693 | [Climbing Stairs II](problems/3693-climbing-stairs-ii) | `1` | 🟡 Medium |
| 3717 | [Minimum Operations to Make Elements Within K Subarrays Equal](problems/3717-minimum-operations-to-make-elements-within-k-subarrays-equal) | `1` | 🔴 Hard |
| 3723 | [Maximize Sum of Squares of Digits](problems/3723-maximize-sum-of-squares-of-digits) | `1` | 🟡 Medium |
| 3727 | [Maximum Alternating Sum of Squares](problems/3727-maximum-alternating-sum-of-squares) | `1` | 🟡 Medium |
| 3731 | [Find Missing Elements](problems/3731-find-missing-elements) | `1` | 🟢 Easy |
| 3736 | [Minimum Moves to Equal Array Elements III](problems/3736-minimum-moves-to-equal-array-elements-iii) | `1` | 🟢 Easy |
| 3789 | [Minimum Cost to Acquire Required Items](problems/3789-minimum-cost-to-acquire-required-items) | `2` | 🟡 Medium |
| 3841 | [Palindromic Path Queries in a Tree](problems/3841-palindromic-path-queries-in-a-tree) | `1` | 🔴 Hard |
| 3863 | [Minimum Operations to Sort a String](problems/3863-minimum-operations-to-sort-a-string) | `1` | 🟡 Medium |
| 3882 | [Minimum XOR Path in a Grid](problems/3882-minimum-xor-path-in-a-grid) | `1` | 🟡 Medium |
| 3886 | [Sum of Sortable Integers](problems/3886-sum-of-sortable-integers) | `1` | 🔴 Hard |
| 3942 | [Minimum Operations to Sort a Permutation](problems/3942-minimum-operations-to-sort-a-permutation) | `1` | 🟡 Medium |
| 3963 | [Create Grid With Exactly One Path](problems/3963-create-grid-with-exactly-one-path) | `1` | 🟢 Easy |
| 3970 | [Shortest Path With At Most K Consecutive Identical Characters](problems/3970-shortest-path-with-at-most-k-consecutive-identical-characters) | `1` | 🟡 Medium |
| 4043 | [Count Rotations With Exactly K Equal Adjacent Pairs](problems/4043-count-rotations-with-exactly-k-equal-adjacent-pairs) | `1` | 🟢 Easy |
| 4044 | [Count Good Cyclic Rotations](problems/4044-count-good-cyclic-rotations) | `1` | 🟡 Medium |
| 4048 | [Count Values With Equally Spaced Occurrences I](problems/4048-count-values-with-equally-spaced-occurrences-i) | `1` | 🟢 Easy |
| 4049 | [Count Values With Equally Spaced Occurrences II](problems/4049-count-values-with-equally-spaced-occurrences-ii) | `1` | 🟡 Medium |
| 4050 | [Minimum Days to Score Exactly N Points](problems/4050-minimum-days-to-score-exactly-n-points) | `1` | 🟡 Medium |
| 4052 | [Cyclically Shift Rows and Columns](problems/4052-cyclically-shift-rows-and-columns) | `1` | 🟢 Easy |
| 4061 | [Minimum Queen Moves to Reach Target](problems/4061-minimum-queen-moves-to-reach-target) | `1` | 🟢 Easy |
| 4062 | [Transform Array Using Pair Operations](problems/4062-transform-array-using-pair-operations) | `1` | 🟡 Medium |
| 4063 | [Longest Subarray Divisible by K with At Most One Negation I](problems/4063-longest-subarray-divisible-by-k-with-at-most-one-negation-i) | `1` | 🟡 Medium |
| 4070 | [Minimum Rotations to Dial a Number I](problems/4070-minimum-rotations-to-dial-a-number-i) | `1` | 🟢 Easy |
| 4071 | [Minimum Rotations to Dial a Number II](problems/4071-minimum-rotations-to-dial-a-number-ii) | `1` | 🟡 Medium |
| 4074 | [Maximum Product Pair With Target Sum](problems/4074-maximum-product-pair-with-target-sum) | `1` | 🟢 Easy |
| 4075 | [Longest Resilient Subarray I](problems/4075-longest-resilient-subarray-i) | `1` | 🟡 Medium |
| 4078 | [Three Fibonacci Sum](problems/4078-three-fibonacci-sum) | `1` | 🟢 Easy |
| 4079 | [Prime Subset Selection I](problems/4079-prime-subset-selection-i) | `1` | 🟡 Medium |
<!-- PROBLEMS_TABLE_END -->

> This table updates **automatically** via GitHub Actions every time a new `.java` solution is pushed to `main`, using LeetCode's own search API to fetch the correct title, link, and difficulty — even if the filename has typos or shorthand.

---

## 🎯 NeetCode 150 Progress

<!-- PROGRESS_START -->
```
[#################---]  128 / 150 solved
```
<!-- PROGRESS_END -->

<!-- PATTERN_TABLE_START -->
| Pattern | Status |
|:---|:---:|
| Arrays & Hashing | 🟢 In Progress |
| Two Pointers | 🟢 In Progress |
| Sliding Window | 🟢 In Progress |
| Stack | 🟢 In Progress |
| Binary Search | 🟢 In Progress |
| Linked List | ⬜ Not Started |
| Trees | ⬜ Not Started |
| Tries | ⬜ Not Started |
| Heap / Priority Queue | ⬜ Not Started |
| Backtracking | ⬜ Not Started |
| Graphs | ⬜ Not Started |
| Advanced Graphs | ⬜ Not Started |
| 1-D Dynamic Programming | ⬜ Not Started |
| 2-D Dynamic Programming | ⬜ Not Started |
| Greedy | ⬜ Not Started |
| Intervals | ⬜ Not Started |
| Math & Geometry | ⬜ Not Started |
| Bit Manipulation | ⬜ Not Started |
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
