class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);
        int low=nums[0];
        int high=nums[nums.length-1];
        int count=low;
        List<Integer> missing=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]==count){
                count++;
            }else if(nums[i]!=count && count<high){
                missing.add(count);
                i--;
                count++;
            }
        }
        return missing;
    }
}
