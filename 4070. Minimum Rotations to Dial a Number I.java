class Solution {
    public int minRotations(String s) {
        int rotations=0;
        int prev=0;
        char[] numbers=s.toCharArray();
        for(int i=0;i<s.length();i++){
            int cur=s.charAt(i)-'0';
            int dif=Math.abs(cur-prev);
            rotations+=Math.min(dif,10-dif);
            prev=cur;
        }
        return rotations;
    }
}
