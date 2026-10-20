import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);

        int validKSum = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            if (canSortWithK(nums, sortedNums, n, k)) {
                validKSum += k;
            }
        }

        return validKSum;
    }

    private boolean canSortWithK(int[] nums, int[] sortedNums, int n, int k) {
        for (int start = 0; start < n; start += k) {
            if (!isValidRotatedSegment(nums, sortedNums, start, k)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidRotatedSegment(int[] nums, int[] sortedNums, int start, int k) {
        int targetMin = sortedNums[start];
        int targetMax = sortedNums[start + k - 1];

        int actualMin = nums[start];
        int actualMax = nums[start];
        int drops = 0;

        for (int i = 0; i < k; i++) {
            int curr = nums[start + i];
            int next = nums[start + ((i + 1) % k)];

            if (curr > next) {
                drops++;
                if (drops > 1) {
                    return false;
                }
            }

            if (curr < actualMin) actualMin = curr;
            if (curr > actualMax) actualMax = curr;
        }

        // Segment ka min/max boundary target sorted range ke sath match hona chahiye
        return actualMin == targetMin && actualMax == targetMax;
    }
}
