import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + k2;
        
        int[] diff = new int[n];
        long totalDiffSum = 0;
        int maxD = 0;
        
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            totalDiffSum += diff[i];
            if (diff[i] > maxD) {
                maxD = diff[i];
            }
        }
        
        // If we have enough moves to zero out every difference
        if (totalK >= totalDiffSum) {
            return 0L;
        }
        
        // Binary search for the smallest threshold T such that operations <= totalK
        int left = 0, right = maxD;
        int targetCap = maxD;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long requiredOps = 0;
            
            for (int d : diff) {
                if (d > mid) {
                    requiredOps += (d - mid);
                }
            }
            
            if (requiredOps <= totalK) {
                targetCap = mid;   // mid is achievable, try smaller cap
                right = mid - 1;
            } else {
                left = mid + 1;    // mid requires too many operations
            }
        }
        
        // Calculate remaining moves after reducing all diffs > targetCap down to targetCap
        long usedOps = 0;
        for (int d : diff) {
            if (d > targetCap) {
                usedOps += (d - targetCap);
            }
        }
        long leftoverK = totalK - usedOps;
        
        // Cap elements and distribute leftover operations
        long minSumSquare = 0;
        for (int i = 0; i < n; i++) {
            long current = diff[i];
            if (current > targetCap) {
                current = targetCap;
            }
            
            // Further reduce some elements from targetCap to targetCap - 1
            if (current == targetCap && leftoverK > 0 && current > 0) {
                current--;
                leftoverK--;
            }
            
            minSumSquare += current * current;
        }
        
        return minSumSquare;
    }
}
