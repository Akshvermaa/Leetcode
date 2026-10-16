class Solution {
    static final long MOD=1_000_000_007L;
    long[]sum,sumSq,lazy;
    int n;
    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        sumSq = new long[4 * n];
        lazy = new long[4 * n];
        int[] last = new int[100001];
        Arrays.fill(last, -1);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];
            update(1, 0, n - 1, prev + 1, i, 1);
            ans = (ans + sumSq[1]) % MOD;
            last[nums[i]] = i;
        }
        return (int) ans;
    }
    void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        update(node * 2, l, mid, ql, qr, val);
        update(node * 2 + 1, mid + 1, r, ql, qr, val);
        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sumSq[node] = (sumSq[node * 2] + sumSq[node * 2 + 1]) % MOD;
    }
    void apply(int node, int l, int r, long val) {
        long len = r - l + 1L;
        sumSq[node] = (sumSq[node]
                + 2L * val % MOD * sum[node] % MOD
                + len % MOD * val % MOD * val % MOD) % MOD;

        sum[node] = (sum[node] + len % MOD * val) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }
    void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) return;
        int mid = (l + r) / 2;
        apply(node * 2, l, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, r, lazy[node]);

        lazy[node] = 0;
    }
}