class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] ans =new int[2];
        int i=0;
        int j=numbers.length-1;
        int flag=1;
        while(flag!=0){
            if(numbers[i]+numbers[j]==target){
               flag=0;
               ans[0]=i+1;
               ans[1]=j+1;
            }else if(numbers[i]+numbers[j]>target){
                j--;
            }else if(numbers[i]+numbers[j]<target){
                i++;
            }
        }
        return ans;
    }
}
