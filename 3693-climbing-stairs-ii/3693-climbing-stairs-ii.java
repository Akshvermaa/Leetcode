class Solution {
    public int climbStairs(int n, int[] costs) {
        int[]dp=new int [n+1];
        for(int i=1;i<=n;i++){
            dp[i]=Integer.MAX_VALUE;
            for(int j=Math.max(0,i-3);j<i;j++){
                int jumpCost=costs[i-1]+(i-j)*(i-j);
                dp[i]=Math.min(dp[i],dp[j]+jumpCost);
            }
        }
        return dp[n];
    }
}