/*
 * Problem: 3789. Minimum Cost to Acquire Required Items
 * Link: https://leetcode.com/problems/minimum-cost-to-acquire-required-items/
 * Language: java
 * Status: Accepted
 * Runtime: 2 ms (beats 42.25%)
 * Memory: 42.4 MB (beats 56.34%)
 * Date: 2026-11-11 19:37:29
 */

class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long effectiveCost1 = Math.min((long) cost1, (long) costBoth);
        long effectiveCost2 = Math.min((long) cost2, (long) costBoth);
        long effectiveCostBoth = Math.min((long) costBoth, effectiveCost1 + effectiveCost2);

        long common = Math.min(need1, need2);
        long rem1 = need1 - common;
        long rem2 = need2 - common;

        return common * effectiveCostBoth + rem1 * effectiveCost1 + rem2 * effectiveCost2;
    }
}