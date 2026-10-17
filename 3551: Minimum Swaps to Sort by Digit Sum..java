import java.util.Arrays;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;

        // items[i] = {digit_sum, value, original_index}
        int[][] items = new int[n][3];
        for (int i = 0; i < n; i++) {
            items[i][0] = getDigitSum(nums[i]);
            items[i][1] = nums[i];
            items[i][2] = i;
        }

        // Sort primarily by digit_sum ascending, secondarily by value ascending
        Arrays.sort(items, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        boolean[] visited = new boolean[n];
        int totalSwaps = 0;

        // Traverse permutation cycles
        for (int i = 0; i < n; i++) {
            // Skip if already visited or already at its correct target position
            if (visited[i] || items[i][2] == i) {
                continue;
            }

            int cycleSize = 0;
            int curr = i;

            while (!visited[curr]) {
                visited[curr] = true;
                // Move to the next index in the cycle
                curr = items[curr][2];
                cycleSize++;
            }

            if (cycleSize > 1) {
                totalSwaps += (cycleSize - 1);
            }
        }

        return totalSwaps;
    }

    private int getDigitSum(int val) {
        int sum = 0;
        while (val > 0) {
            sum += val % 10;
            val /= 10;
        }
        return sum;
    }
}
