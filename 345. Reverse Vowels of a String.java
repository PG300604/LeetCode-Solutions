class Solution {
    public String reverseVowels(String s) {
        List<Character> vowels = new ArrayList<>();
        
        // Step 1: सारे वोवेल्स को लिस्ट में स्टोर करो (वही आपकी if कंडीशन)
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isVowel(c)) {
                vowels.add(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        int l = vowels.size() - 1; // पीछे से वोवेल उठाने के लिए पॉइंटर

        // Step 2: स्ट्रिंग बनाओ, वोवेल मिलने पर लिस्ट से रिवर्स ऑर्डर में उठाओ
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (!isVowel(c)) {
                sb.append(c);
            } else {
                sb.append(vowels.get(l));
                l--;
            }
        }

        return sb.toString();
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
               c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
