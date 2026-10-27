class Solution {
    public int countIslands(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        int answer = 0;

        int[][] directions = {
            {1, 0},   // down
            {-1, 0},  // up
            {0, 1},   // right
            {0, -1}   // left
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Skip water or already visited cells
                if (grid[i][j] == 0 || visited[i][j]) {
                    continue;
                }

                long sum = 0;

                // DFS stack
                java.util.Stack<int[]> stack = new java.util.Stack<>();

                stack.push(new int[]{i, j});
                visited[i][j] = true;

                while (!stack.isEmpty()) {

                    int[] current = stack.pop();

                    int r = current[0];
                    int c = current[1];

                    // Add current cell
                    sum += grid[r][c];

                    // Check 4 directions
                    for (int[] dir : directions) {

                        int nr = r + dir[0];
                        int nc = c + dir[1];

                        if (nr >= 0 && nr < m &&
                            nc >= 0 && nc < n &&
                            grid[nr][nc] > 0 &&
                            !visited[nr][nc]) {

                            visited[nr][nc] = true;
                            stack.push(new int[]{nr, nc});
                        }
                    }
                }

                // Check whether island sum is divisible by k
                if (sum % k == 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}