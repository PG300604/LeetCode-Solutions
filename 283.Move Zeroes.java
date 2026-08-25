class Solution {
    public void moveZeroes(int[] nums) {
        int insertPos = 0; // वह जगह जहाँ अगला non-zero एलिमेंट रखा जाएगा

        // Step 1: सारे non-zero एलिमेंट्स को आगे शिफ्ट करो
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[insertPos] = nums[i];
                insertPos++;
            }
        }

        // Step 2: बाकी बची हुई सारी पोजीशन्स को 0 बना दो
        while (insertPos < nums.length) {
            nums[insertPos] = 0;
            insertPos++;
        }
    }
}
