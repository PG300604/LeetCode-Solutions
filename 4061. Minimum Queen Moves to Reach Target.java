class Solution {
    public int minQueenMoves(int[] source, int[] target) {
        int sr=source[0];
        int sc=source[1];
        int tr=target[0];
        int tc=target[1];
        int flag=0;
        while(flag==0){
            if(sc==tc && sr==tr){
                flag=1;
            }else if(sc==tc && sr!=tr){
                sr=tr;
                flag=1;
                return 1;
            }else if(sr==tr && sc!=tc){
                sc=tc;
                flag=1;
                return 1;
            }else if(sr!=tr && sc!=tc && Math.abs(sr-tr)==Math.abs(sc-tc)){
                sr=tr;
                sc=tc;
                flag=1;
                return 1;
            }else if(sr!=tr && sc!=tc && Math.abs(sr-tr)!=Math.abs(sc-tc)){
                sr=tr;
                sc=tc;
                flag=1;
                return 2;
            }
        }
        return 0;
    }
}
