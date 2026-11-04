/**
 * Problem: Length of Last Word
 * Problem Number: 58
 * Date: 2026-09-21
 */
class Solution {
    public int lengthOfLastWord(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
    }
}
