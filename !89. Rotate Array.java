class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n <= 1) return;
        
        k %= n;
        if (k == 0) return;

        int[] temp = new int[k];
        // Copy last k elements to temp
        System.arraycopy(nums, n - k, temp, 0, k);
        // Shift remaining n - k elements to the right
        System.arraycopy(nums, 0, nums, k, n - k);
        // Copy temp back to front of nums
        System.arraycopy(temp, 0, nums, 0, k);
    }
}
