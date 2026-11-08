/*
 * Problem: 1385. Find the Distance Value Between Two Arrays
 * Link: https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
 * Language: java
 * Status: Accepted
 * Runtime: 7 ms (beats 29.90%)
 * Memory: 46.2 MB (beats 60.07%)
 * Date: 2026-11-05 21:14:54
 */

import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // 1. Sort arr2 so we can use binary search
        Arrays.sort(arr2);
        int distanceValue = 0;
        
        for (int num1 : arr1) {
            // 2. Search for any element in arr2 that falls in the range [num1 - d, num1 + d]
            if (isValid(arr2, num1 - d, num1 + d)) {
                distanceValue++;
            }
        }
        
        return distanceValue;
    }
    
    private boolean isValid(int[] arr, int from, int to) {
        int low = 0;
        int high = arr.length - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            // If we find ANY element inside the invalid range, this num1 is invalid
            if (arr[mid] >= from && arr[mid] <= to) {
                return false; 
            } else if (arr[mid] < from) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return true; // No element from arr2 found in the invalid range
    }
}
