class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeroesCount = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            // 1. Expand window: if current element is 0, increment count
            if (nums[right] == 0) {
                zeroesCount++;
            }

            // 2. Shrink window: if invalid (too many zeros), move left forward
            while (zeroesCount > k) {
                if (nums[left] == 0) {
                    zeroesCount--; // Dropped a zero out of the window
                }
                left++; // Advance left boundary
            }

            // 3. Window is now guaranteed valid (zeroesCount <= k)
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
