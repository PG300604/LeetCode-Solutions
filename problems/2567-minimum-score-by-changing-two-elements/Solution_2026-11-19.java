/*
 * Problem: 2567. Minimum Score by Changing Two Elements
 * Link: https://leetcode.com/problems/minimum-score-by-changing-two-elements/
 * Language: java
 * Status: Accepted
 * Runtime: 18 ms (beats 88.54%)
 * Memory: 59.4 MB (beats 46.87%)
 * Date: 2026-11-19 17:16:24
 */

import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        // If there are 3 elements, we can make all 3 equal, resulting in a score of 0
        if (n <= 3) {
            return 0;
        }
        
        Arrays.sort(nums);
        
        // Case 1: Change the two smallest elements
        int option1 = nums[n - 1] - nums[2];
        
        // Case 2: Change the two largest elements
        int option2 = nums[n - 3] - nums[0];
        
        // Case 3: Change one smallest and one largest element
        int option3 = nums[n - 2] - nums[1];
        
        return Math.min(option1, Math.min(option2, option3));
    }
}