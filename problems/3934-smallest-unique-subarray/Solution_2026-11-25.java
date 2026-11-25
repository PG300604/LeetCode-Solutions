/*
 * Problem: 3934. Smallest Unique Subarray
 * Link: https://leetcode.com/problems/smallest-unique-subarray/
 * Language: java
 * Status: Accepted
 * Runtime: 451 ms (beats 50.00%)
 * Memory: 209.8 MB (beats 60.71%)
 * Date: 2026-11-25 18:20:59
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 1_000_033L;
    private static final long BASE2 = 1_000_037L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int low = 1, high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueSubarray(nums, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;
        long h1 = 0, h2 = 0;
        long p1 = 1, p2 = 1;

        // Precompute BASE^(len - 1) % MOD
        for (int i = 0; i < len - 1; i++) {
            p1 = (p1 * BASE1) % MOD1;
            p2 = (p2 * BASE2) % MOD2;
        }

        Map<Long, Integer> freq = new HashMap<>(n - len + 1);

        for (int i = 0; i < n; i++) {
            // Offset values by +1 to avoid 0-value hash collisions
            long val = nums[i] + 1L;

            if (i >= len) {
                long outVal = nums[i - len] + 1L;
                h1 = (h1 - (outVal * p1) % MOD1 + MOD1) % MOD1;
                h2 = (h2 - (outVal * p2) % MOD2 + MOD2) % MOD2;
            }

            h1 = (h1 * BASE1 + val) % MOD1;
            h2 = (h2 * BASE2 + val) % MOD2;

            if (i >= len - 1) {
                long combinedHash = (h1 << 32) | h2;
                freq.put(combinedHash, freq.getOrDefault(combinedHash, 0) + 1);
            }
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}