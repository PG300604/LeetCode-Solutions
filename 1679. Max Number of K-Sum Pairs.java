import java.util.Arrays;

class Solution {
    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        int operations = 0;

        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == k) {
                operations++;
                i++; // Pair is used, advance both pointers
                j--;
            } else if (sum > k) {
                j--;
            } else {
                i++;
            }
        }

        return operations;
    }
}
