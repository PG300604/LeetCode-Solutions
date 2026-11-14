/*
 * Problem: 3364. Minimum Positive Sum Subarray
 * Link: https://leetcode.com/problems/minimum-positive-sum-subarray/
 * Language: java
 * Status: Accepted
 * Runtime: 4 ms (beats 75.51%)
 * Memory: 45.9 MB (beats 79.80%)
 * Date: 2026-11-14 16:50:35
 */

import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;

        for (int i = 0; i <= n - l; i++) {
            int currentSum = 0;
            // Extend the subarray up to length r or the end of the list
            for (int j = i; j < n && j < i + r; j++) {
                currentSum += nums.get(j);
                int len = j - i + 1;

                if (len >= l && currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }

        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}