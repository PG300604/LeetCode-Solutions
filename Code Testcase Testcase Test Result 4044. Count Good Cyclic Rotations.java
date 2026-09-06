class Solution {
    public int countGoodRotations(int[] nums) {
        int n = nums.length;
        int k = n / 2;
        
        long totalSum = 0;
        for (int x : nums) {
            totalSum += x;
        }

        long firstHalf = 0;
        for (int i = 0; i < k; i++) {
            firstHalf += nums[i];
        }

        int goodCount = 0;
        for (int i = 0; i < n; i++) {
            long secondHalf = totalSum - firstHalf;
            if (secondHalf > firstHalf) {
                goodCount++;
            }
            firstHalf += nums[(i + k) % n] - nums[i];
        }

        return goodCount;
    }
}
