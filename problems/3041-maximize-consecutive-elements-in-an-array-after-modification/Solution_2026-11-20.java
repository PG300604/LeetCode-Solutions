/*
 * Problem: 3041. Maximize Consecutive Elements in an Array After Modification
 * Link: https://leetcode.com/problems/maximize-consecutive-elements-in-an-array-after-modification/
 * Language: java
 * Status: Accepted
 * Runtime: 96 ms (beats 33.33%)
 * Memory: 112.7 MB (beats 60.61%)
 * Date: 2026-11-20 17:56:11
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        // Sort the array to build sequences in increasing order
        Arrays.sort(nums);
        
        // dp.get(v) stores the max length of a consecutive sequence ending at value v
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 0;
        
        for (int x : nums) {
            // 1. Increment x to (x + 1): extends any sequence ending at x
            int lenPlusOne = dp.getOrDefault(x, 0) + 1;
            dp.put(x + 1, lenPlusOne);
            
            // 2. Keep x as x: extends any sequence ending at (x - 1)
            int lenKeep = dp.getOrDefault(x - 1, 0) + 1;
            dp.put(x, lenKeep);
            
            // Update the global maximum length found so far
            maxLen = Math.max(maxLen, Math.max(lenPlusOne, lenKeep));
        }
        
        return maxLen;
    }
}