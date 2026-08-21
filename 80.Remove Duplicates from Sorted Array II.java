class Solution {
    public int removeDuplicates(int[] nums) {
        // If array has 2 or fewer elements, it already satisfies the condition
        if (nums.length <= 2) return nums.length;

        // k points to the write index for the next valid element
        int k = 2;

        for (int i = 2; i < nums.length; i++) {
            // Compare current element with the element placed at (k - 2)
            if (nums[i] != nums[k - 2]) {
                nums[k] = nums[i];
                k++;
            }
        }

        return k;
    }
}
