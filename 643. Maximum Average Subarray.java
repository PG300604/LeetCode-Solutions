class Solution {
    public double findMaxAverage(int[] nums, int k) {
        // 1. Compute the sum of the first window
        long sum = 0; // Use long to prevent integer overflow for large values
        for (int j = 0; j < k; j++) {
            sum += nums[j];
        }

        long maxSum = sum;

        // 2. Slide the window from index k to the end of the array
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k]; // Add new right element, subtract left element leaving window
            maxSum = Math.max(maxSum, sum);
        }

        // 3. Cast to double at the end to prevent integer division truncation
        return (double) maxSum / k;
    }
}
