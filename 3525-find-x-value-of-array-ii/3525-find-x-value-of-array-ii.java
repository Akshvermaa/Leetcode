class Solution {
    static class Node {
        int product;
        int[] cnt;
        Node(int k) {
            cnt = new int[k];
        }
    }
    int n, k;
    Node[] tree;
    Node merge(Node left, Node right) {
        Node res = new Node(k);
        res.product = (left.product * right.product) % k;
        for (int r = 0; r < k; r++) {
            res.cnt[r] += left.cnt[r];
        }
        for (int r = 0; r < k; r++) {
            int nr = (left.product * r) % k;
            res.cnt[nr] += right.cnt[r];
        }
        return res;
    }
    Node createNode(int value) {
        Node node = new Node(k);
        node.product = value % k;
        node.cnt[node.product] = 1;
        return node;
    }
    void build(int idx, int l, int r, int[] nums) {
        if (l == r) {
            tree[idx] = createNode(nums[l]);
            return;
        }
        int mid = (l + r) / 2;
        build(idx * 2, l, mid, nums);
        build(idx * 2 + 1, mid + 1, r, nums);
        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }
    void update(int idx, int l, int r, int pos, int value) {
        if (l == r) {
            tree[idx] = createNode(value);
            return;
        }
        int mid = (l + r) / 2;
        if (pos <= mid) {
            update(idx * 2, l, mid, pos, value);
        } else {
            update(idx * 2 + 1, mid + 1, r, pos, value);
        }
        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }
    Node query(int idx, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[idx];
        }
        int mid = (l + r) / 2;
        if (qr <= mid) {
            return query(idx * 2, l, mid, ql, qr);
        }
        if (ql > mid) {
            return query(idx * 2 + 1, mid + 1, r, ql, qr);
        }
        Node left = query(idx * 2, l, mid, ql, qr);
        Node right = query(idx * 2 + 1, mid + 1, r, ql, qr);
        return merge(left, right);
    }
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        tree = new Node[4 * n];
        build(1, 0, n - 1, nums);
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            update(1, 0, n - 1, index, value);
            Node res = query(1, 0, n - 1, start, n - 1);
            result[i] = res.cnt[x];
        }
        return result;
    }
}