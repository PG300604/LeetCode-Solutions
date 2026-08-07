import java.util.Arrays;

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        Arrays.sort(nums);

        int longest = 1;
        int currentStreak = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                continue;
            }
            if (nums[i + 1] == nums[i] + 1) {
                currentStreak++;
            } else {
                currentStreak = 1;
            }

            longest = Math.max(longest, currentStreak);
        }

        return longest;
    }
}
