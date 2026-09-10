class Solution {
    private int find(String t, char c, int index){
        if(index<t.length()){
            for(int i=index;i<t.length();i++){
              if(t.charAt(i)==c){
                return i;
                }
            }
        }
        return -1;
    }
    public boolean isSubsequence(String s, String t) {
        int n=s.length();
        int s_index=0;
        int t_index=0;
        while(s_index <=n-1){
            int ti=find(t,s.charAt(s_index),t_index);
            if(ti==-1){
                return false;
            }else{
                s_index++;
                t_index=ti+1;
            }
        }
        return true;
    }
}
