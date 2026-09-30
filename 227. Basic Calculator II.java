class Solution {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;

        int len = s.length();
        int[] stack = new int[len]; // Fast array stack (Java Stack class se 10x tez)
        int top = -1;
        
        int currentNumber = 0;
        char operation = '+';

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
            }

            if ((!Character.isDigit(c) && c != ' ') || i == len - 1) {
                if (operation == '+') {
                    stack[++top] = currentNumber;
                } else if (operation == '-') {
                    stack[++top] = -currentNumber;
                } else if (operation == '*') {
                    stack[top] = stack[top] * currentNumber;
                } else if (operation == '/') {
                    stack[top] = stack[top] / currentNumber;
                }
                operation = c;
                currentNumber = 0;
            }
        }

        int result = 0;
        while (top >= 0) {
            result += stack[top--];
        }
        return result;
    }
}
