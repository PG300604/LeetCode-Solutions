class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // Check if we have seen this number before
            if (map.containsKey(nums[i])) {
                int prevIndex = map.get(nums[i]);
                if (i - prevIndex <= k) {
                    return true; // Found a pair within distance k
                }
            }
            // Update map with the current (most recent) index
            map.put(nums[i], i);
        }

        return false;
    }
}
