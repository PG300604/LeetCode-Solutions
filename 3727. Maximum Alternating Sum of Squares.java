import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] sq = new long[n];
        
        for (int i = 0; i < n; i++) {
            sq[i] = (long) nums[i] * nums[i];
        }
        
        Arrays.sort(sq);
        
        long total = 0;
        int negCount = n / 2; // the n / 2 smallest elements get subtracted
        
        // Smallest elements are subtracted
        for (int i = 0; i < negCount; i++) {
            total -= sq[i];
        }
        
        // Remaining elements (the largest) are added
        for (int i = negCount; i < n; i++) {
            total += sq[i];
        }
        
        return total;
    }
}
