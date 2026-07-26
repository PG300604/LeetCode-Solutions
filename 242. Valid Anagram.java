class Solution {
    public boolean isAnagram(String s, String t) {
        int sl=s.length();
        int tl=t.length();
        if(sl!=tl){
            return false;
        }
        int[] buckets = new int[26];
        for(int i=0;i<sl;i++){
            buckets[s.charAt(i) - 'a']++;
            buckets[t.charAt(i) - 'a']--;
        }
        for(int count : buckets){
            if(count!=0){
                return false;
            }
        }
        return true;
    }
}
