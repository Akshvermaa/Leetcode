class Solution {
    public int maxPalindromes(String s, int k) {
        int n=s.length();
        boolean[][]dp=new boolean[n][n];
        int[]ans=new int [n+1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i - 1];

            for (int j = 0; j <= i - k; j++) {
                if (dp[j][i - 1]) {
                    ans[i] = Math.max(ans[i], ans[j] + 1);
                }
            }
        }
        return ans[n];
    }
}