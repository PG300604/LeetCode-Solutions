/**
 * Problem: Minimum Add to Make Parentheses Valid
 * Problem Number: 921
 * Date: 2026-10-06
 */
class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int ans = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    ans++;
                }
            }
        }

        return ans + open;
    }
}
