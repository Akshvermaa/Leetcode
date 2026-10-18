class Solution {
    static final long MOD=1000000007L;
    long[][]comb=new long[1001][1001];
    public int numOfWays(int[] nums) {
        int n=nums.length;
        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return (int) ((solve(list) - 1 + MOD) % MOD);
    }
    private long solve(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }
        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }
        long leftWays = solve(left);
        long rightWays = solve(right);
        int leftSize = left.size();
        int rightSize = right.size();
        long ways = comb[leftSize + rightSize][leftSize];
        ways = ways * leftWays % MOD;
        ways = ways * rightWays % MOD;

        return ways;
    }
}