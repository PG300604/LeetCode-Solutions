class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // 1. Locate index of 0
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx = i;
                break;
            }
        }

        // 2. Check circularly increasing: nums[(idx + k) % n] == k
        boolean inc = true;
        for (int k = 0; k < n; k++) {
            if (nums[(idx + k) % n] != k) {
                inc = false;
                break;
            }
        }

        if (inc) {
            if (idx == 0) return 0;
            // Option 1: Just rotate left
            int opt1 = idx;
            // Option 2: Reverse -> rotate -> reverse
            int opt2 = 1 + (n - idx) + 1;
            return Math.min(opt1, opt2);
        }

        // 3. Check circularly decreasing: nums[(idx + k) % n] == (n - k) % n
        boolean dec = true;
        for (int k = 0; k < n; k++) {
            if (nums[(idx + k) % n] != (n - k) % n) {
                dec = false;
                break;
            }
        }

        if (dec) {
            // Option 1: Reverse first, then rotate left
            int opt1 = 1 + (n - 1 - idx);
            // Option 2: Rotate left first, then reverse
            int opt2 = ((idx + 1) % n) + 1;
            return Math.min(opt1, opt2);
        }

        return -1;
    }
}
