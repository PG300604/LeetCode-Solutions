class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        // Push 0 as the base score for the outermost layer
        stack.push(0);

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                // Enter a deeper nesting level
                stack.push(0);
            } else {
                // Pop the score accumulated inside the closing pair
                int innerScore = stack.pop();

                // If innerScore is 0, it was an empty pair "()" -> evaluates to 1
                // Otherwise, it was a nested group "(A)" -> evaluates to 2 * innerScore
                int currentVal = (innerScore == 0) ? 1 : 2 * innerScore;

                // Add this value to the parent enclosing context
                int parentScore = stack.pop();
                stack.push(parentScore + currentVal);
            }
        }

        return stack.pop();
    }
}
