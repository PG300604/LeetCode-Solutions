<div align="center">
<img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=0,2,2,5,30&height=120&section=header&text=LeetCode%20Solutions&fontSize=34&fontColor=ffffff&fontAlignY=42&desc=Priyanshu%20Ghosh%20%7C%20Java%20%7C%20DSA%20Grind&descAlignY=65&descSize=14&descColor=79b8ff&animation=fadeIn" width="100%"/>
</div>

<br/>

<div align="center">

[![LeetCode](https://img.shields.io/badge/LeetCode-priyanshughosh30-FFA116?style=for-the-badge&logo=leetcode&logoColor=black)](https://leetcode.com/priyanshughosh30/)
[![Java](https://img.shields.io/badge/Language-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)

</div>

<br/>

<!-- ╔══════════════════════════════════════════════════════════════════╗
     ║                       LIVE STATS DASHBOARD                      ║
     ╚══════════════════════════════════════════════════════════════════╝ -->

<div align="center">

<img src="https://leetcard.jacoblin.cool/priyanshughosh30?theme=dark&font=Karma&ext=heatmap" alt="LeetCode Stats"/>

</div>

<br/>

<div align="center">

![LeetCode Stats](https://leetcode-stats-two-lyart.vercel.app/api?username=priyanshughosh30&theme=dark&background=0d1117&border=1A56DB&stroke=1A56DB&ring=79b8ff&fire=ff6b35&currStreakLabel=79b8ff&sideLabels=8fa3c0&dates=8fa3c0&currStreakNum=ffffff&sideNums=ffffff)

</div>

<br/>

<!-- ╔══════════════════════════════════════════════════════════════════╗
     ║                         PROGRESS TRACKER                        ║
     ╚══════════════════════════════════════════════════════════════════╝ -->

## 📊 Topic-Wise Progress

<div align="center">

| Topic | Status | Problems |
|:---|:---:|:---:|
| Arrays & Strings | 🟢 In Progress | — |
| Hashing | 🟢 In Progress | — |
| Two Pointers | ⬜ Not Started | — |
| Sliding Window | ⬜ Not Started | — |
| Linked List | ⬜ Not Started | — |
| Stacks & Queues | ⬜ Not Started | — |
| Binary Search | ⬜ Not Started | — |
| Trees | ⬜ Not Started | — |
| Graphs | ⬜ Not Started | — |
| Dynamic Programming | ⬜ Not Started | — |
| Greedy | ⬜ Not Started | — |
| Heaps | ⬜ Not Started | — |
| Backtracking | ⬜ Not Started | — |
| Tries | ⬜ Not Started | — |

</div>

> Update the **Status** and **Problems** columns as you go. Use 🟢 In Progress, ✅ Complete, ⬜ Not Started.

---

## 📁 Repository Structure

```
leetcode-solutions/
├── 01-arrays-strings/
│   ├── two-sum/
│   │   ├── Solution.java
│   │   └── README.md          # Problem link, approach, complexity
│   └── ...
├── 02-hashing/
├── 03-two-pointers/
├── 04-sliding-window/
├── 05-linked-list/
├── 06-stacks-queues/
├── 07-binary-search/
├── 08-trees/
├── 09-graphs/
├── 10-dynamic-programming/
├── 11-greedy/
├── 12-heaps/
├── 13-backtracking/
├── 14-tries/
└── README.md
```

---

## 📝 Solution Template

Each problem folder follows this format:

**`Solution.java`**
```java
/**
 * LeetCode #1 - Two Sum
 * https://leetcode.com/problems/two-sum/
 *
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

**`README.md`** *(per problem, optional but recommended)*
```markdown
## Two Sum
**Difficulty:** Easy
**Pattern:** Hashing

### Approach
Use a HashMap to store seen values and their indices. For each number,
check if its complement (target - num) already exists in the map.

### Complexity
- Time: O(n)
- Space: O(n)
```

---

## 📌 Recently Solved

<!-- Update this table manually as you solve problems -->

| # | Problem | Difficulty | Pattern | Solution |
|---|---------|:---:|---|---|
| 1 | Two Sum | 🟢 Easy | Hashing | [Link](./01-arrays-strings/two-sum/) |

<!-- Add new rows above this line as you go -->

---

## 🎯 Current Goal

```
[▓▓░░░░░░░░░░░░░░░░░░]  Target: 150 problems before internship season
Focus: Arrays, Hashing, Two Pointers, Sliding Window (interview-critical patterns)
```

---

## 🔗 Related

- 📄 [Data Structures and Algorithms: The Complete Masterclass](https://github.com/PG300604) — Udemy certification
- 💼 [Main GitHub Profile](https://github.com/PG300604)
- 🔗 [LinkedIn](https://linkedin.com/in/priyanshu-ghosh-)

<div align="center">
<img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=0,2,2,5,30&height=80&section=footer" width="100%"/>
</div>
