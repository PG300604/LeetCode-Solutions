/*
 * Problem: 3105. Longest Strictly Increasing or Strictly Decreasing Subarray
 * Link: https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/
 * Language: java
 * Status: Accepted
 * Runtime: 2 ms (beats 14.94%)
 * Memory: 43.8 MB (beats 85.71%)
 * Date: 2026-11-12 08:02:51
 */

class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        if(nums.length==0){
            return 0;
        }else if(nums.length==1){
            return 1;
        }
        int left=0;
        int right=left+1;
        int flag=0;
        int current_len=1;
        int max_len=current_len;
        if(nums[left]>nums[right]){
            flag=1;
            current_len++;
            max_len=Math.max(max_len,current_len);
        }else if(nums[left]<nums[right]){
            flag=0;
            current_len++;
            max_len=Math.max(max_len,current_len);
        }
        while(right<nums.length-1){
            right++;
            if(flag==0 && nums[right-1]<nums[right]){
                current_len++;
                max_len=Math.max(max_len,current_len);
            }else if(flag==0 && nums[right-1]>nums[right]){
                left=right;
                flag=1;
                current_len=1;
                current_len++;
                max_len=Math.max(max_len,current_len);
            }else if((flag==0 || flag==1)&&(nums[right-1]==nums[right])){
                left=right;
                current_len=1;
            }else if(flag==1 && nums[right-1]<nums[right]){
                left=right;
                flag=0;
                current_len=1;
                current_len++;
                max_len=Math.max(max_len,current_len);
            }else if(flag==1 && nums[right-1]>nums[right]){
                current_len++;
                max_len=Math.max(max_len,current_len);
            }
        }
        return max_len;
    }
}