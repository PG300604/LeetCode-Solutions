class Solution {
    private void dfs(int[][] grid,int r,int c){
        if(r<0 || r>=grid.length || c<0 || c>=grid[0].length || grid[r][c]==1){
            return;
        }
        grid[r][c]=1;

        dfs(grid,r+1,c);
        dfs(grid,r-1,c);
        dfs(grid,r,c+1);
        dfs(grid,r,c-1);
    }
    public int closedIsland(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        for(int r=0;r<m;r++){
            if(grid[r][0]==0) dfs(grid,r,0);
            if(grid[r][n-1]==0) dfs(grid,r,n-1);
        }
        for(int c=0;c<n;c++){
            if(grid[0][c]==0) dfs(grid,0,c);
            if(grid[m-1][c]==0) dfs(grid,m-1,c);
        }
        int closedIslands =0;
        for(int r=1;r<m-1;r++){
            for(int c=1;c<n-1;c++){
                if(grid[r][c]==0){
                    closedIslands++;
                    dfs(grid,r,c);
                }
            }
        }
        return closedIslands;
    }
}
