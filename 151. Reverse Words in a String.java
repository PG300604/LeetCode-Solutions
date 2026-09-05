class Solution {
    private StringBuilder word(String s,int st,int end){
        StringBuilder sb1=new StringBuilder();
        sb1.setLength(0);
        for(int i=st;i<end;i++){
            sb1.append(s.charAt(i));
        }
        return sb1;
    }
    public String reverseWords(String s) {
        int n=s.length();
        int st=0;
        StringBuilder sb=new StringBuilder();
        List<StringBuilder> list=new ArrayList<>();
        for (int i = 0; i <= n; i++) {
        if (i == n || s.charAt(i) == ' ') {
            if (st < i) { 
               list.add(word(s, st, i));
            }
            st = i + 1;
            }
        }
        for(int j = list.size() - 1; j >= 0; j--){
            sb.append(list.get(j));
            if(j!=0){
                sb.append(" ");
            }
        }
        String res=sb.toString();
        return res;
    }
}
