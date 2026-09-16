class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> answer =new ArrayList<>();
        List<Integer> list1=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();
        Map<Integer,Integer> map=new HashMap<>();
        for(int i : nums1){
            map.put(i,0);
        }
        for(int j: nums2){
            if(!map.containsKey(j)){
                list2.add(j);
                map.put(j,0);
            }
        }
        map.clear();
        for(int i : nums2){
            map.put(i,0);
        }
        for(int j: nums1){
            if(!map.containsKey(j)){
                list1.add(j);
                map.put(j,0);
            }
        }
        answer.add(list1);
        answer.add(list2);
        return answer;
    }
}
