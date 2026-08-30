class Solution {
    public int missingNumber(int[] nums) {
        int n=nums.length;
        int sumofall=(n*(n+1))/2;
        int missing=sumofall;
        for(int i : nums){
            missing=missing-i;
        }
        return missing;
    }
}
