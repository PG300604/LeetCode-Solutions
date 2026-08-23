class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        // Step 1: Put all elements of nums1 into the map with frequency/flag 1
        for (int num : nums1) {
            map.put(num, 1);
        }

        // Step 2: Iterate through nums2
        for (int num : nums2) {
            // If the element exists in nums1 and has not been added to result yet
            if (map.containsKey(num) && map.get(num) == 1) {
                list.add(num);
                // Mark as 0 so duplicate occurrences in nums2 are ignored
                map.put(num, 0); 
            }
        }

        // Step 3: Convert List to int[]
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }
}
