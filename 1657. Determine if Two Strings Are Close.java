class Solution {
    public boolean closeStrings(String word1, String word2) {
        Map<Character,Integer> map1=new HashMap<>();
        Map<Character,Integer> map2=new HashMap<>();
        for(int i=0;i<word1.length();i++){
            map1.put(word1.charAt(i),map1.getOrDefault(word1.charAt(i),0)+1);
        }
        for(int i=0;i<word2.length();i++){
            map2.put(word2.charAt(i),map2.getOrDefault(word2.charAt(i),0)+1);
        }
        if(word1.length()!=word2.length()){
            return false;
        }
        if(!map1.keySet().equals(map2.keySet())){
            return false;
        }
        for(Map.Entry<Character,Integer> entry : map1.entrySet()){
            if(map2.containsValue(entry.getValue())){
                map2.values().remove(entry.getValue());
            }else{
                return false;
            }
        }
        return true;
    }
}
