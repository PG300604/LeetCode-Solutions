/**
 * Problem: Find All Numbers Disappeared in an Array
 * Problem Number: 448
 * Date: 2026-08-28
 */
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n=nums.length;
        Map<Integer,Integer> map=new HashMap<>();
        List<Integer> list=new ArrayList<>();
        for(int j: nums){
            map.put(j,0);
        }
        for(int i=1;i<=n;i++){
            if(!map.containsKey(i)){
                list.add(i);
            }
        }
        return list;
    }
}
