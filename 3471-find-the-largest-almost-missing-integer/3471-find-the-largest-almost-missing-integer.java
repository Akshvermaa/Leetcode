class Solution {
    public int largestInteger(int[] nums, int k) {
        int[] count = new int[51];

        for (int i = 0; i <= nums.length - k; i++) {
            boolean[] seen = new boolean[51];

            for (int j = i; j < i + k; j++) {
                if (!seen[nums[j]]) {
                    seen[nums[j]] = true;
                    count[nums[j]]++;
                }
            }
        }

        for (int i = 50; i >= 0; i--) {
            if (count[i] == 1) {
                return i;
            }
        }

        return -1;
    }
}