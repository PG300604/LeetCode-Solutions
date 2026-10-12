class Solution {
    public boolean isPrime(int num){
        if(num<=1)return false;
        if(num<=3)return true;
        if(num%2==0||num%3==0)return false;
        for(int i=5;i*i<=num;i+=6){
            if(num%i==0||num%(i+2)==0){
                return false;
            }
        }
        return true;
    }
    public List<Integer> maxPrimes(int n, int s) {
        List<Integer> prime=new ArrayList<>();
        List<Integer> res=new ArrayList<>();
        for(int i=2;i<=n;i++){
            if(isPrime(i)){
                prime.add(i);
            }
        }
        long sum=0;
        int dig=s;
        for(int i : prime){
            if(sum+i<=dig){
                sum+=i;
                res.add(i);
            }else{
                break;
            }
        }
        return res;
    }
}
