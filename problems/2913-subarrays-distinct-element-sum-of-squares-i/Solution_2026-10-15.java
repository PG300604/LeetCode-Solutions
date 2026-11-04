/**
 * Problem: Subarrays Distinct Element Sum of Squares I
 * Problem Number: 2913
 * Date: 2026-10-15
 */
class Solution {
    public int sumCounts(List<Integer> nums) {
        int result = 0;
        int n = nums.size();
        
        for(int i =0; i< n; i++) {
            int count = 0;
            boolean[] visited = new boolean[101];
            for(int j=i; j< n; j++) {
                int num = nums.get(j);
                if(!visited[num]) {
                    visited[num] = true;
                    count++;
                }
                result += count*count;
            }
            
        }

        return result;
    }
}
