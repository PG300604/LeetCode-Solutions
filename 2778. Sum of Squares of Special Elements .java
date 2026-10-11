class Solution {
    public int sumOfSquares(int[] nums) {
        int n=nums.length;
        int ssse=0;
        for(int i=1;i<=n;i++){
            int dig=nums[i-1];
            if(n%i==0){
                ssse+=(dig*dig);
            }
        }
        return ssse;
    }
}
