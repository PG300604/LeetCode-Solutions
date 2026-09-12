class Solution {
    public int minDays(int n) {
        int [] points=new int[n+1];
        Arrays.fill(points, Integer.MAX_VALUE/2);
        points[0]=0;
        for(int i=1;;i++){
            long triple =(long)i*(i+1)/2;
            if(triple >n){
                break;
            }
            int val =(int)triple;
            int cost=i+1;
            for(int s=val; s<=n;s++){
                points[s]=Math.min(points[s], points[s-val]+cost);
            }
        }
        return points[n]-1;
    }
}
