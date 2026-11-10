/*
 * Problem: 3693. Climbing Stairs II
 * Link: https://leetcode.com/problems/climbing-stairs-ii/
 * Language: java
 * Status: Accepted
 * Runtime: 8 ms (beats 65.25%)
 * Memory: 116.3 MB (beats 82.00%)
 * Date: 2026-11-10 20:47:36
 */

import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            int stepCost = costs[i - 1];

            // Jump of 1 step: (1)^2 = 1
            if (i >= 1 && dp[i - 1] != Integer.MAX_VALUE) {
                dp[i] = Math.min(dp[i], dp[i - 1] + 1 + stepCost);
            }
            // Jump of 2 steps: (2)^2 = 4
            if (i >= 2 && dp[i - 2] != Integer.MAX_VALUE) {
                dp[i] = Math.min(dp[i], dp[i - 2] + 4 + stepCost);
            }
            // Jump of 3 steps: (3)^2 = 9
            if (i >= 3 && dp[i - 3] != Integer.MAX_VALUE) {
                dp[i] = Math.min(dp[i], dp[i - 3] + 9 + stepCost);
            }
        }

        return dp[n];
    }
}