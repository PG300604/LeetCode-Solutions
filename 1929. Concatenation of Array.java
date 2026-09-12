class Solution {
    public int[] getConcatenation(int[] nums) {
        int n=nums.length;
        int[] sol=new int[n*2];
        System.arraycopy(nums,0,sol,0,n);
        System.arraycopy(nums,0,sol,n,n);
        return sol;
    }
}
