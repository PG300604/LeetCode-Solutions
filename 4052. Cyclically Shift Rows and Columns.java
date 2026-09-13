class Solution {
    public int[][] cyclicShift(int n, int[][] grid, int[] rowShift, int[] colShift) {
        int[][] inter=new int[n][n];
        int[][] res=new int[n][n];
        for(int i=0;i<n;i++){
            int o=rowShift[i]%n;
            for(int j=0;j<n;j++){
                int newCol=(j-o+n)%n;
                inter[i][newCol]=grid[i][j];
            }
        }
        for(int k=0;k<n;k++){
            int p=colShift[k]%n;
            for(int i=0;i<n;i++){
                int newRow=(i-p+n)%n;
                res[newRow][k]=inter[i][k];
            }
        }
        return res;
    }
}
