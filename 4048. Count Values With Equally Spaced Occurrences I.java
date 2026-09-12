class Solution {
    private boolean indexcheck(int[] nums, int k){
        List<Integer> index=new ArrayList<>();
        index.clear();
        for(int i=0;i<nums.length;i++){
            if(nums[i]==k){
                index.add(i);
            }
        }
        if (index.get(1) - index.get(0) == index.get(2) - index.get(1)){
            return true;
        }
        return false;
    }
    public int countSpecialIntegers(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        List<Integer> appeared3=new ArrayList<>();
        int count=0;
        for(int i : nums){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(entry.getValue()==3){
                appeared3.add(entry.getKey());
            }
        }
        for(int j: appeared3){
          if(indexcheck(nums, j)==true){
              count++;
          }
        }
        return count;
    }
}
