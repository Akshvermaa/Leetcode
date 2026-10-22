class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        // -----------------------------------------
        // CASE 1: nums is a LEFT rotation
        // of [0, 1, 2, ..., n-1]
        // -----------------------------------------

        int pos0 = 0;

        while (nums[pos0] != 0) {
            pos0++;
        }

        boolean ok = true;

        for (int i = 0; i < n; i++) {
            if (nums[(pos0 + i) % n] != i) {
                ok = false;
                break;
            }
        }

        if (ok) {
            /*
             * Normally:
             * left rotate pos0 times
             *
             * But we can also do the opposite direction:
             * Reverse -> left rotate -> Reverse
             *
             * That costs:
             * 2 + (n - pos0)
             */
            ans = Math.min(pos0, 2 + (n - pos0));
        }

        // -----------------------------------------
        // CASE 2: nums is a rotation of
        // [n-1, n-2, ..., 1, 0]
        // -----------------------------------------

        int posMax = 0;

        while (nums[posMax] != n - 1) {
            posMax++;
        }

        ok = true;

        for (int i = 0; i < n; i++) {
            int index = (posMax + i) % n;

            if (nums[index] != n - 1 - i) {
                ok = false;
                break;
            }
        }

        if (ok) {
            /*
             * Reverse + rotations.
             *
             * We can rotate in either direction before/after
             * the reverse.
             */
            ans = Math.min(ans, 1 + Math.min(posMax, n - posMax));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}