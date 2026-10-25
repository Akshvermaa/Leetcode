class Solution {
    public int closedIsland(int[][] grid) {
        int rows =grid.length;
        int cols=grid[0].length;
        int count=0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    if (dfs(grid, i, j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    public boolean dfs(int[][] grid, int r, int c) {

        // Went outside the grid

        if (r < 0 || r >= grid.length ||
            c < 0 || c >= grid[0].length) {
            return false;
        }

        // Water

        if (grid[r][c] == 1) {
            return true;
        }

        // Mark visited

        grid[r][c] = 1;
        boolean up = dfs(grid, r - 1, c);
        boolean down = dfs(grid, r + 1, c);
        boolean left = dfs(grid, r, c - 1);
        boolean right = dfs(grid, r, c + 1);
        return up && down && left && right;
    }
}