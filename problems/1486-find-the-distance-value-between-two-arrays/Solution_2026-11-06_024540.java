/*
 * Problem: 1486. Find the Distance Value Between Two Arrays
 * Link: https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
 * Language: java
 * Status: Accepted
 * Runtime: 2 ms (beats 99.79%)
 * Memory: 45.7 MB (beats 87.61%)
 * Date: 2026-11-05 21:15:40
 */

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
           int c = 0;
        for(int n : arr1){
            if(isValid(n, arr2, d)) c++;
        }
        return c;
    }
    boolean isValid(int n, int[] arr, int d){
        for(int e : arr){
            if(Math.abs(e - n) <= d) return false;
        }
        return true;

    }
}