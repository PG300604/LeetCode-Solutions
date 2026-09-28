class Solution {
    public int longestSubarray(int[] nums, int k) {
        int n=nums.length;
        int maxLen=0;
        long currentSum=0;

        int[] first =new int[k];
        Arrays.fill(first,-1);
        first[0]=0;
        int[] withneg=new int[k];
        Arrays.fill(withneg,Integer.MAX_VALUE);

        for(int i=0;i<n;i++){
            currentSum+=nums[i];
            int cur=(int)((currentSum%k+k)%k);

            int d=(int)(((2L * nums[i])%k+k)%k);
            for(int rem=0;rem<k;rem++){
                if(first[rem]!=-1 && first[rem]<=i){
                    int target=(rem+d)%k;
                    withneg[target]=Math.min(withneg[target],first[rem]);
                }
            }
            if(first[cur]!=-1){
                maxLen=Math.max(maxLen,(i+1)-first[cur]);
            }
            if(withneg[cur]!=Integer.MAX_VALUE){
                maxLen=Math.max(maxLen,(i+1)-withneg[cur]);
            }
            if(first[cur]==-1){
                first[cur]=i+1;
            }
        }
        return maxLen;
    }
}
