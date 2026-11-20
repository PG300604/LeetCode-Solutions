/*
 * Problem: 3041. Maximize Consecutive Elements in an Array After Modification
 * Link: https://leetcode.com/problems/maximize-consecutive-elements-in-an-array-after-modification/
 * Language: java
 * Status: Accepted
 * Runtime: 35 ms (beats 96.97%)
 * Memory: 72.3 MB (beats 100.00%)
 * Date: 2026-11-20 17:56:40
 */

class Solution {
  public int maxSelectedElements(int[] nums) {
    int ans = 1;
    int prev = Integer.MIN_VALUE;
    // the length of the longest consecutive elements (seq0) ending in the
    // previous number
    int dp0 = 1;
    // the length of the longest consecutive elements (seq1) ending in the
    // previous number + 1
    int dp1 = 1;

    Arrays.sort(nums);

    for (final int num : nums) {
      if (num == prev) {
        dp1 = dp0 + 1; // Append `num + 1` to seq0.
      } else if (num == prev + 1) {
        ++dp0; // Append `num` to seq0.
        ++dp1; // Add 1 to every number in seq0 and append `num + 1` to seq0.
      } else if (num == prev + 2) {
        dp0 = dp1 + 1; // Append `num` to seq1.
        dp1 = 1;       // Start a new sequence [`num + 1`].
      } else {
        dp0 = 1; // Start a new sequence [`num`].
        dp1 = 1; // Start a new sequence [`num + 1`].
      }
      ans = Math.max(ans, Math.max(dp0, dp1));
      prev = num;
    }

    return ans;
  }
}