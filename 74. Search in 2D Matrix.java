class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int top = 0;
        int bottom = m - 1;
        int targetRow = -1;
        
        // Step 1: Binary search to find row
        while (top <= bottom) {
            int midRow = top + (bottom - top) / 2;
            
            if (target > matrix[midRow][n - 1]) {
                top = midRow + 1;
            } else if (target < matrix[midRow][0]) {
                bottom = midRow - 1;
            } else {
                targetRow = midRow;
                break;
            }
        }
        
        if (targetRow == -1) {
            return false;
        }
        
        // Step 2: Binary search inside the selected row
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (matrix[targetRow][mid] == target) {
                return true;
            } else if (matrix[targetRow][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
}
