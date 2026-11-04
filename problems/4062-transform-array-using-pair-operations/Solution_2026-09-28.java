/**
 * Problem: Transform Array Using Pair Operations
 * Problem Number: 4062
 * Date: 2026-09-28
 */
class Solution {
    public boolean canTransform(int[] source, int[] target) {
        long sumsource=0;
        long sumtarget=0;
        if(source.length != target.length){
            return false;
        }
        if(source.length==1){
            return source[0]==target[0];
        }
        for(int i=0;i<source.length;i++){
            sumsource+=source[i];
            sumtarget+=target[i];
        }
        return sumsource==sumtarget;
    }
}
