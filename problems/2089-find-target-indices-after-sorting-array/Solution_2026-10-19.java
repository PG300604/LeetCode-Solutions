/**
 * Problem: Find Target Indices After Sorting Array
 * Problem Number: 2089
 * Date: 2026-10-19
 */
class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target){
                list.add(i);
            }else{
                continue;
            }
        }
        return list;
    }
}
