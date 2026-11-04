/**
 * Problem: Remove Elements
 * Problem Number: 27
 * Date: 2026-08-04
 */
class Solution {
    public int removeElement(int[] nums, int val) {
      int k=0;
      for(int i=0;i<nums.length;i++){
          if(nums[i]!=val){
              nums[k]=nums[i];
              k++;
            }
        }
      return k;
    }
}
