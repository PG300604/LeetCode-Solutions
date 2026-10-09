class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRight = 0; // Number of ')' needed

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If we needed an odd number of ')', the previous ')' was unpaired.
                // Insert one ')' right now to pair it up before handling this '('.
                if (neededRight % 2 != 0) {
                    insertions++;
                    neededRight--; // Now it's even
                }
                neededRight += 2;
            } else {
                neededRight--;
                // If we see ')' without a preceding '(', we must insert a '('
                if (neededRight < 0) {
                    insertions++;     // Insert '('
                    neededRight = 1;  // That '(' gives 2 needs, minus current ')' = 1
                }
            }
        }

        return insertions + neededRight;
    }
}
