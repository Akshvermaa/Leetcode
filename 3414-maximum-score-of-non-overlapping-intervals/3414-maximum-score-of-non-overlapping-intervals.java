class Solution {
    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
          int n = intervals.size();
        Interval[] a = new Interval[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }
        Arrays.sort(a, (x, y) -> {
            if (x.l != y.l) return Integer.compare(x.l, y.l);
            return Integer.compare(x.r, y.r);
        });
        long[][] dp = new long[n + 1][5];
        List<Integer>[][] paths = new ArrayList[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                paths[i][k] = new ArrayList<>();
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            int next = lowerBound(a, i + 1, n, a[i].r + 1);
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = dp[i + 1][k];
                paths[i][k] = new ArrayList<>(paths[i + 1][k]);
                if (k < 4) {
                    long take = a[i].w + dp[next][k + 1];
                    List<Integer> candidate = new ArrayList<>();
                    candidate.add(a[i].idx);
                    candidate.addAll(paths[next][k + 1]);
                    Collections.sort(candidate);
                    if (take > dp[i][k] ||
                        (take == dp[i][k] && lexicographicallySmaller(candidate, paths[i][k]))) {
                        dp[i][k] = take;
                        paths[i][k] = candidate;
                    }
                }
            }
        }
        return paths[0][0].stream().mapToInt(Integer::intValue).toArray();
    }
    static int lowerBound(Interval[] a, int left, int right, long target) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (a[mid].l >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    static boolean lexicographicallySmaller(List<Integer> a, List<Integer> b) {
        int n = Math.min(a.size(), b.size());
        for (int i = 0; i < n; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() < b.size();
    }
}