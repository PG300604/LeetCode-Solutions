class Solution {
    // Helper returns long to prevent 32-bit integer overflow during summation
    public long total(int[][] grid, int i, int j, long total_no) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] <= 0) {
            return total_no;
        }

        total_no += grid[i][j];
        grid[i][j] = -1; // Mark as visited

        total_no = total(grid, i + 1, j, total_no);
        total_no = total(grid, i - 1, j, total_no);
        total_no = total(grid, i, j + 1, total_no);
        total_no = total(grid, i, j - 1, total_no);

        return total_no;
    }

    // LeetCode signature remains int
    public int countIslands(int[][] grid, int k) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 0) {
                    long local = total(grid, i, j, 0L);
                    if (local % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
