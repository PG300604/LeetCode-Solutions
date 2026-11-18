/*
 * Problem: 2914. Minimum Number of Changes to Make Binary String Beautiful
 * Link: https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/
 * Language: java
 * Status: Accepted
 * Runtime: 3 ms (beats 97.56%)
 * Memory: 45.9 MB (beats 92.68%)
 * Date: 2026-11-18 18:07:35
 */

class Solution {
    public int minChanges(String s) {
        int changes = 0;
        
        // Iterate through adjacent pairs (0,1), (2,3), (4,5), ...
        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++;
            }
        }
        
        return changes;
    }
}