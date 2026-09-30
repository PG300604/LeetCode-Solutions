import java.util.*;

class Solution {
    public String removeKdigits(String num, int k) {
        int len = num.length();
        if (k == len) return "0";

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            char c = num.charAt(i);

            // Jab tak current digit stack ke top wale digit se chhota hai,
            // picche wale bade digit ko hatao taaki number chhota bane
            while (!stack.isEmpty() && k > 0 && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        // Agar number strictly increasing order mein tha (jaise "12345"),
        // toh end (top of stack) se bade numbers pop kar lo
        while (k > 0) {
            stack.pop();
            k--;
        }

        // StringBuilder mein result build karo
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

        // Leading zeroes hatao
        int nonZeroIndex = 0;
        while (nonZeroIndex < sb.length() && sb.charAt(nonZeroIndex) == '0') {
            nonZeroIndex++;
        }

        String res = sb.substring(nonZeroIndex);
        return res.isEmpty() ? "0" : res;
    }
}
