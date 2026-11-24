/*
 * Problem: 3411. Maximum Subarray With Equal Products
 * Link: https://leetcode.com/problems/maximum-subarray-with-equal-products/
 * Language: java
 * Status: Accepted
 * Runtime: 2 ms (beats 96.84%)
 * Memory: 42.9 MB (beats 100.00%)
 * Date: 2026-11-24 18:07:09
 */

class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }

        int maxLen = 2;
        int left = 0;
        int windowProd = 1;

        for (int right = 0; right < n; right++) {
            // Shrink window until nums[right] is coprime with every element in the window
            while (gcd(windowProd, nums[right]) > 1) {
                windowProd /= nums[left];
                left++;
            }

            windowProd *= nums[right];
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}