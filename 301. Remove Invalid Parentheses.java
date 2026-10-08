class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    private void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
        int count = 0;
        for (int i = last_i; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) count++;
            if (s.charAt(i) == par[1]) count--;
            if (count >= 0) continue;

            // Found an invalid closing bracket at or before index i
            for (int j = last_j; j <= i; j++) {
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
                    remove(s.substring(0, j) + s.substring(j + 1), ans, i, j, par);
                }
            }
            return;
        }

        // Finished one direction: reverse to handle unmatched opening brackets
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        } else {
            ans.add(reversed); // Both directions balanced
        }
    }
}
