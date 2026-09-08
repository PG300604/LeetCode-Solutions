class Solution {
    public int compress(char[] chars) {
        int writeIndex = 0;

        for (int i = 0; i < chars.length;) {
            char current = chars[i];
            int count = 0;

            // Count consecutive occurrences
            while (i < chars.length && chars[i] == current) {
                i++;
                count++;
            }

            // Write the character
            chars[writeIndex++] = current;

            // Write the count only if > 1
            if (count > 1) {
                for (char digit : String.valueOf(count).toCharArray()) {
                    chars[writeIndex++] = digit;
                }
            }
        }

        return writeIndex;
    }
}   
