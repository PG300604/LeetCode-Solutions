class Solution {
    public int longestSubarray(int[] nums) {
        int count_1 = 0; // 1s before the current zero
        int count_2 = 0; // 1s after the current zero
        int max_sum = 0;
        int right = 0;
        int flag = 0;     // 1 if we are currently holding a deleted 0
        boolean hasZero = false;

        while (right < nums.length) {
            if (nums[right] == 1 && flag == 0) {
                count_1++;
                max_sum = Math.max(max_sum, count_1);
                right++;
            } else if (nums[right] == 1 && flag == 1) {
                count_2++;
                max_sum = Math.max(max_sum, count_1 + count_2);
                right++;
            } else if (nums[right] == 0 && flag == 0) {
                // First zero encountered
                hasZero = true;
                flag = 1;
                right++;
            } else {
                // Second (or consecutive) zero encountered
                hasZero = true;
                count_1 = count_2; // count_2 now becomes the segment before this new 0
                count_2 = 0;
                flag = 1;
                right++;
            }
        }

        // If there were no zeros in the array, you must delete one '1'
        if (!hasZero) {
            return nums.length - 1;
        }

        return max_sum;
    }
}
