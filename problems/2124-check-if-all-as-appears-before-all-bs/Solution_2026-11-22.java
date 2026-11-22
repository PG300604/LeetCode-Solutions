/*
 * Problem: 2124. Check if All A's Appears Before All B's
 * Link: https://leetcode.com/problems/check-if-all-as-appears-before-all-bs/
 * Language: java
 * Status: Accepted
 * Runtime: 0 ms (beats 100.00%)
 * Memory: 42.6 MB (beats 35.49%)
 * Date: 2026-11-22 17:30:36
 */

class Solution {
    public boolean checkString(String s) {
        return !s.contains("ba");
    }
}