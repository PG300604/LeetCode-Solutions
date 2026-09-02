class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Integer.MIN_VALUE;
        for (Integer num : candies) {
           if (num > max) {
               max = num;
            }
        }
        List<Boolean> result=new ArrayList<>();
        for(int j : candies){
            if(j+extraCandies>=max){
                result.add(true);
            }else{
                result.add(false);
            }
        }
        return result;
    }
}
