class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                minOpen++;
                maxOpen++;
            } else if (ch == ')') {
                minOpen--;
                maxOpen--;
            } else { // ch == '*'
                minOpen--; // treat as ')'
                maxOpen++; // treat as '('
            }

            // More ')' than possible '(' + '*'
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot drop below 0 (a '*' can just be empty)
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        return minOpen == 0;
    }
}
