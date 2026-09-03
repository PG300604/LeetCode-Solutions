class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count=0;
        int zeroes=1;
        for(int i : flowerbed){
            if(i==0){
                zeroes++;
            }else{
                count=count+((zeroes-1)/2);
                zeroes=0;
            }
        }
        count+=zeroes/2;
        if(n<=count){
            return true;
        }else{
            return false;
        }
    }
}
