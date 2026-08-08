class Solution {
    public boolean isPalindrome(String s) {
        String f = s.replaceAll("[^a-zA-Z0-9]", "");
        f = f.toLowerCase();
        int j = 0;
        for (int i = f.length() - 1; i >= 0; i--) {
            if (f.charAt(j) != f.charAt(i) && j < f.length()) {
                return false;
            }
            j++;
        }
        return true;
    }
}
