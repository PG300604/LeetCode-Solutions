class Solution {
    public String mergeAlternately(String word1, String word2) {
        int w1l=word1.length();
        int w2l=word2.length();
        int maxwl=Math.max(w1l,w2l);
        int index=0;
        StringBuilder sb = new StringBuilder("");
        while(index<maxwl){
            if(index<w1l){
                sb.append(word1.charAt(index));
            }
            if(index<w2l){
                sb.append(word2.charAt(index));
            }
            index++;
        }
        String result=sb.toString();
        return result;
    }
}
