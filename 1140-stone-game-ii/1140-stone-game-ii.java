class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n + 1];
        int[] suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }
        return solve(0, 1, piles, suffix, dp);
    }
    private int solve(int i, int m, int[] piles, int[] suffix, int[][] dp) {
        int n = piles.length;
        if (i >= n) return 0;
        if (2 * m >= n - i) return suffix[i];
        if (dp[i][m] != 0) return dp[i][m];
        int result = 0;
        for (int x = 1; x <= 2 * m; x++) {
            result = Math.max(result, suffix[i] - solve(i + x, Math.max(m, x), piles, suffix, dp));
        }
        return dp[i][m] = result;
    }
}