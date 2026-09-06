class Solution {
    public int countRotations(String s, int k) {
        int n = s.length();
        if(n<=1){
            return (k==0)?n:0;
        }
        int totalcircular=0;
        for(int i=0;i<n;i++){
            if(s.charAt(i)==s.charAt((i+1)%n)){
                totalcircular++;
            }
        }
        int validrotation=0;
        for(int i=0;i<n;i++){
            int broken=(s.charAt((i-1+n)%n)==s.charAt(i))?1:0;
            int rotationscore=totalcircular-broken;
            if(rotationscore ==k){
                validrotation++;
            }
        }
        return validrotation;
    }
}
