class Solution {
    private boolean valid(String n){
        int i=0;
        while(i<n.length()-1){
            if(n.charAt(i)<=n.charAt(i+1)){
                i++;
            }else{
                return false;
            }
        }
        return true;
    }
    public int minOperations(String s) {
        int n=s.length();
        char mn = s.charAt(0);
        char mx = s.charAt(0);
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if (c < mn) mn = c;
            if (c > mx) mx = c;
        }
        if(valid(s)){
            return 0;
        }else{
            if(n==2){
                return -1;
            }else if (s.charAt(0) == mn || s.charAt(n - 1) == mx) {
                return 1;
            }else{
                for (int i = 1; i < n - 1; i++) {
                  char c = s.charAt(i);
                  if (c == mn || c == mx) {
                      return 2;
                  }
                }  
            }
        }
        return 3;
    }
}
