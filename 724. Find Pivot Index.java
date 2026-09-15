class Solution {
    public int pivotIndex(int[] nums) {
        int total_sum=0;
        int left_sum=0;
        for(int i : nums){
            total_sum+=i;
        }
        for(int j=0; j<nums.length; j++){
            if(left_sum==total_sum-left_sum-nums[j]){
                return j;
            }
            left_sum+=nums[j];
         
        }
        return -1;
    }
}
