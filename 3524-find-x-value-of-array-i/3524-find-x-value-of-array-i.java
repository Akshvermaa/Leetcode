class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[]dp=new long[k];
        long[]ans=new long[k];
        for (int num : nums) {
            long[] next = new long[k];
            int val = num % k;
            next[val]++;
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (r * val) % k;
                    next[newRem] += dp[r];
                }
            }
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }
            dp = next;
        }
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = (int) ans[i];
        }
        return ans;
    }
}