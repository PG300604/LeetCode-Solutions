/*
 * Problem: 1267. Count Servers that Communicate
 * Link: https://leetcode.com/problems/count-servers-that-communicate/
 * Language: java
 * Status: Accepted
 * Runtime: 2ms (beats 100.00%)
 * Memory: 55.05MB (beats 100.00%)
 * Date: 2026-11-08 20:23:37
 */

class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        // Step 1: Count total servers in each row and column
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    rowCount[r]++;
                    colCount[c]++;
                }
            }
        }

        // Step 2: Count servers that share a row or column with at least one other server
        int communicableServers = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    if (rowCount[r] > 1 || colCount[c] > 1) {
                        communicableServers++;
                    }
                }
            }
        }

        return communicableServers;
    }
}