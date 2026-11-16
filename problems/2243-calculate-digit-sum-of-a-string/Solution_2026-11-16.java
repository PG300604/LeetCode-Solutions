/*
 * Problem: 2243. Calculate Digit Sum of a String
 * Link: https://leetcode.com/problems/calculate-digit-sum-of-a-string/
 * Language: java
 * Status: Accepted
 * Runtime: 1 ms (beats 98.69%)
 * Memory: 42.2 MB (beats 96.07%)
 * Date: 2026-11-16 18:18:55
 */

class Solution {
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            StringBuilder nextRound = new StringBuilder();
            
            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;
                // Sum digits in the current group of size up to k
                for (int j = i; j < Math.min(i + k, s.length()); j++) {
                    sum += s.charAt(j) - '0';
                }
                nextRound.append(sum);
            }
            
            s = nextRound.toString();
        }
        
        return s;
    }
}