class Solution {
    private int gcd(int a,int b){
        while(b!=0){
            int temp=b;
            b=a%b;
            a=temp;
        }
        return a;
    }
    public int resilientSubarray(int[] nums, int k) {
        if(nums.length==0){
            return 0;
        }
        int max_len=1;
        int n=nums.length;
        int left=0;
        while(left<n){
            int right=left;
            int rem=((nums[left]%k)+k)%k;
            while(right<n && ((nums[right]%k)+k)%k==rem){
                right++;
            }
            int count=right-left;
            if(rem==0){
                max_len=Math.max(max_len,count);
            }else{
                int step=k/gcd(rem,k);
                int valid=1+((count-1)/step)*step;
                max_len=Math.max(max_len,valid);
            }
            left=right;
        }
        return max_len;
    }
}
