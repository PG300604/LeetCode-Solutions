/**
 * Problem: Minimum Moves to Equal Array Elements II
 * Problem Number: 462
 * Date: 2026-11-02
 */
class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int moves=0;
        int n=0;
        if(nums.length%2==0){
            n= (nums.length/2)-1;
        }else{
            n= nums.length/2;
        }
        int me=nums[n];
        for(int i=0;i<nums.length;i++){
            if(nums[i]<=me){
                moves+=(me-nums[i]);
            }else{
                moves+=(nums[i]-me);
            }
        }
        return moves;
    }
}
