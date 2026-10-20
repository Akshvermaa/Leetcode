class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);

        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int cnt = map.getOrDefault(x, 0);
            rank[i] = lowerBound(sorted, x) + cnt;
            map.put(x, cnt + 1);
        }

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean valid = true;

            for (int start = 0; start < n && valid; start += k) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                int descents = 0;

                for (int i = start; i < start + k; i++) {
                    min = Math.min(min, rank[i]);
                    max = Math.max(max, rank[i]);

                    int next = start + (i - start + 1) % k;
                    if (nums[i] > nums[next]) {
                        descents++;
                    }
                }

                if (min != start || max != start + k - 1 || descents > 1) {
                    valid = false;
                }
            }

            if (valid) ans += k;
        }

        return ans;
    }

    private int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}