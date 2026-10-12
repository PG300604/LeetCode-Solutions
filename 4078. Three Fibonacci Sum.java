class Solution {
    public static boolean perfectSqure(long num){
        if(num<0)return false;
        long sqrt=(long)Math.sqrt(num);
        return (sqrt*sqrt==num);
    }
    public boolean threeFibonacciSum(int n) {
        if(n%2!=0){
            return false;
        }else{
            long m=n/2;
            long case1=((5*(m*m))+4);
            long case2=((5*(m*m))-4);
            if(perfectSqure(case1)||perfectSqure(case2)){
                return true;
            }
        }
        return false;
    }
}
