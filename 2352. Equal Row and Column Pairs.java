class Solution {
    public int equalPairs(int[][] grid) {
        Map<List,Integer> map=new HashMap<>();
        int count=0;
        for(int i=0;i<grid.length;i++){
            List<Integer> row=new ArrayList<>();
            for(int j=0;j<grid[i].length;j++){
                row.add(grid[i][j]);
            }
            map.put(row,map.getOrDefault(row,0)+1);
        }
        for(int j=0;j<grid[0].length;j++){
            List<Integer> col=new ArrayList<>();
            for(int i=0;i<grid.length;i++){
                col.add(grid[i][j]);
            }
            count+=map.getOrDefault(col,0);
        }
        return count;
    }
}
