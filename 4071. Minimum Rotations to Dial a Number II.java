class Solution {
    public int minRotations(int n, String s) {
        String sol=s;

        int base=0;
        int max=0;
        char last=sol.charAt(n-1);
        char prev='0';
        for(int i=0;i<n;i++){
            char cur =sol.charAt(i);

            int old=dist(prev,cur);
            base+=old;
            int newB=dist(prev,last);
            int gain=old-newB;
            if(gain>max){
                max=gain;
            }
            prev=cur;
        }
        return base-max;
    }
    private int dist(char a, char b){
        int dif=Math.abs(a-b);
        return Math.min(dif,10-dif);
    }
}
