class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1000000007L;
        long[] ans = new long[k];

        for (int bit = 0; bit < 31; bit++) {
            int count = 0;

            for (int num : nums) {
                if ((num & (1 << bit)) != 0) {
                    count++;
                }
            }

            for (int i = 0; i < Math.min(count, k); i++) {
                ans[i] |= (1L << bit);
            }
        }

        long result = 0;

        for (long x : ans) {
            result = (result + (x * x) % MOD) % MOD;
        }

        return (int) result;
    }
}