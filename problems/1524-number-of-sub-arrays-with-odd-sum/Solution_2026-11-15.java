/*
 * Problem: 1524. Number of Sub-arrays With Odd Sum
 * Link: https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/
 * Language: java
 * Status: Accepted
 * Runtime: 8 ms (beats 44.50%)
 * Memory: 81.3 MB (beats 80.95%)
 * Date: 2026-11-15 15:09:06
 */

class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        long count = 0;
        int currentSum = 0;
        
        // Initialize evenCount to 1 to account for an empty prefix (sum = 0)
        int evenCount = 1;
        int oddCount = 0;
        
        for (int num : arr) {
            currentSum += num;
            
            if (currentSum % 2 == 1) {
                // Current prefix sum is odd; pair with all previous even prefix sums
                count = (count + evenCount) % MOD;
                oddCount++;
            } else {
                // Current prefix sum is even; pair with all previous odd prefix sums
                count = (count + oddCount) % MOD;
                evenCount++;
            }
        }
        
        return (int) count;
    }
}