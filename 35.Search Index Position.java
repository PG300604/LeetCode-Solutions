class Solution {
    public int searchInsert(int[] nums, int target) {
        return helper(nums, target, 0, nums.length - 1);
    }

    private int helper(int[] nums, int target, int low, int high) {
        if (low > high) {
            return low;
        }
        int mid = low + (high - low) / 2;
        if (nums[mid] == target) {
            return mid;
        } 
        else if (nums[mid] > target) {
            return helper(nums, target, low, mid - 1);
        } 
        else {
            return helper(nums, target, mid + 1, high);
        }
    }
}
