class Solution {
    private boolean isVowel(char c){
       c = Character.toLowerCase(c);
       return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    public int maxVowels(String s, int k) {
        int current_count=0;
        for(int i=0;i<k;i++){
            if(isVowel(s.charAt(i))){
                current_count++;
            }
        }
        int max_count=current_count;
        max_count=Math.max(max_count,current_count);
        int index_start=0;
        int index_end=k;
        while(index_end<s.length()){
            if(isVowel(s.charAt(index_start))){
                current_count--;
            }
            if(isVowel(s.charAt(index_end))){
                current_count++;
            }
            max_count=Math.max(max_count,current_count);
            index_start++;
            index_end++;
        }
        return max_count;
    }
}
