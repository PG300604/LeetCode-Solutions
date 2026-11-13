/*
 * Problem: 3420. Count Non-Decreasing Subarrays After K Operations
 * Link: https://leetcode.com/problems/count-non-decreasing-subarrays-after-k-operations/
 * Language: java
 * Status: Accepted
 * Runtime: 28 ms (beats 89.29%)
 * Memory: 129.5 MB (beats 71.43%)
 * Date: 2026-11-13 13:25:18
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        
        // Monotonic decreasing deque storing indices of elements 
        // that act as running maximums from left to right.
        Deque<Integer> dq = new ArrayDeque<>();
        int r = n - 1;

        for (int l = n - 1; l >= 0; l--) {
            // 1. Add nums[l] to the left of the window.
            // Any running maximums in the deque smaller than nums[l] 
            // will now be raised to nums[l].
            while (!dq.isEmpty() && nums[dq.peekFirst()] < nums[l]) {
                int currIdx = dq.pollFirst();
                int nextIdx = dq.isEmpty() ? r + 1 : dq.peekFirst();
                long count = nextIdx - currIdx;
                cost += count * (nums[l] - nums[currIdx]);
            }
            dq.addFirst(l);

            // 2. Shrink the window from the right while total cost exceeds k.
            while (cost > k) {
                int maxIdx = dq.peekLast();
                cost -= (nums[maxIdx] - nums[r]);
                if (maxIdx == r) {
                    dq.pollLast();
                }
                r--;
            }

            // 3. All subarrays starting at l and ending at [l...r] are valid.
            ans += (r - l + 1);
        }

        return ans;
    }
}