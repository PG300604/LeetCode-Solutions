class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m=nums1.length;
        int n=nums2.length;
        int[] merged=new int[m+n];
        System.arraycopy(nums1, 0, merged, 0, m);
        System.arraycopy(nums2, 0, merged, m, n);
        Arrays.sort(merged);
        double median;
        int o=merged.length;
        if(o%2==0){
            median=(merged[(o / 2) - 1] + merged[o / 2]) / 2.0;
        }else{
            median=merged[((o+1)/2)-1];
        }
        return median;
    }
}
