class Solution {
    public int[] productExceptSelf(int[] nums) {
        int length=nums.length;
        int[] ans=new int[length];
        ans[0]=1;
        for(int i=1;i<length;i++){
            ans[i]=ans[i-1]*nums[i-1];
        }
        int right=1;
        for(int j=length-1;j>=0;j--){
            ans[j]=ans[j]*right;
            right=right*nums[j];
        }
        return ans;
    }
}
