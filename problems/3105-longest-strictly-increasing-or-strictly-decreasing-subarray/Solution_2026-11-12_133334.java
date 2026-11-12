/*
 * Problem: 3105. Longest Strictly Increasing or Strictly Decreasing Subarray
 * Link: https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/
 * Language: java
 * Status: Accepted
 * Runtime: 1 ms (beats 98.38%)
 * Memory: 43.9 MB (beats 85.71%)
 * Date: 2026-11-12 08:03:34
 */

class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int curI = 1;
        int curD = 1;
        int ans = 1;
        for(int i = 1;i<nums.length;i++){
            if(nums[i]> nums[i-1]){
                curI++;
                curD = 1;
            }
            else if(nums[i]< nums[i-1]){
                curD++;
                curI = 1;
            }
            else{
                curI=1;
                curD=1;
            }
            ans = Math.max(ans,Math.max(curI,curD));
        }
        return ans;
    }
}