class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1; // -1 has all bits set to 1 in two's complement

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                ans &= nums[i];
            }
        }

        // If ans was never modified, nums was already sorted -> return 0
        return ans == -1 ? 0 : ans;
    }
}
