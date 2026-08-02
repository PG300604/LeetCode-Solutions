class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map=new LinkedHashMap<>();
        for(int i : nums){
            if(map.containsKey(i)){
                map.put(i, map.getOrDefault(i, 0) + 1);
            }else{
                map.put(i,0);
            }
        }
        int maxKey =Collections.max(map.entrySet(),Map.Entry.comparingByValue()).getKey();
        return maxKey;
    }
}
