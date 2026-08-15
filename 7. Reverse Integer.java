public class Solution {
    public static int reverse(int x) {
        long reversed = 0;

        while (x != 0) {
            reversed = reversed * 10 + (x % 10);
            x /= 10;
        }

        // Check 32-bit int overflow boundary
        if (reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE) {
            return 0;
        }

        return (int) reversed;
    }
}
