/**
 * Problem: Minimum Moves to Equal Array Elements
 * Problem Number: 453
 * Date: 2026-11-01
 */
class Solution {
    public int minMoves(int[] nums) {
        int count=0;
        int min =nums[0];
        for(int i=0;i<nums.length;i++){
            if(nums[i]<=min){
                min=nums[i];
            }
        }
        for(int i=0;i<nums.length;i++){
            count+=(nums[i]-min);
        }
        return count;
    }
}
