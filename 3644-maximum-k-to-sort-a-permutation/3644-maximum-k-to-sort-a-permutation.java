class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1;

        for (int i = 0; i < nums.length; i++) {

            // If the value is not at its correct index
            if (nums[i] != i) {

                // Keep only the bits common to all misplaced values
                ans &= nums[i];
            }
        }

        // If the array is already sorted, ans is still -1
        return Math.max(ans, 0);
    }
}