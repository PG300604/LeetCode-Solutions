class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        // Stack stores indices of days whose next warmer day is not yet found
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // While the current temperature is warmer than the day on top of the stack
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevDay = stack.pop();
                ans[prevDay] = i - prevDay;
            }
            stack.push(i);
        }

        // Indices left in the stack have no warmer day, default 0 in ans is already correct
        return ans;
    }
}
