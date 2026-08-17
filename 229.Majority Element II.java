class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int n = nums.length;
        int m = n / 3;
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i : nums) {
            // Update frequency
            map.put(i, map.getOrDefault(i, 0) + 1);
            
            // Check condition on every update
            if (map.get(i) > m && !list.contains(i)) {
                list.add(i);
            }
        }
        return list;
    }
}
