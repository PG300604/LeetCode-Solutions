class Solution {
    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;

        for (int i : nums) {
            int count = map.getOrDefault(i, 0);

            if (count == 0) {
                // First time seen: add to sum
                sum += i;
                map.put(i, 1);
            } else if (count == 1) {
                // Duplicate detected: remove its previous contribution
                sum -= i;
                map.put(i, 2);
            }
            // If count >= 2, already subtracted; do nothing to sum
        }

        return sum;
    }
}
