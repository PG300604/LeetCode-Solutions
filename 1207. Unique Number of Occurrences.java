class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer,Integer> map=new HashMap<>();
        Map<Integer,Integer> count=new HashMap<>();
        for(int i : arr){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(!count.containsKey(entry.getValue())){
                count.put(entry.getValue(),0);
            }else{
                return false;
            }
        }
        return true;
    }
}
