class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        //Initially, every subarray has at least one element
        int inc=1;
        int dec=1;
        int maxLength=1;
        // Traverse the array from the second element
        for (int i = 1; i < nums.length; i++) {
            // Check if the current element is greater
            // than the previous element
            if (nums[i] > nums[i - 1]) {
                inc++;       // Increase increasing length
                dec = 1;     // Reset decreasing length
            }
            // Check if the current element is smaller
            // than the previous element
            else if (nums[i] < nums[i - 1]) {
                dec++;       // Increase decreasing length
                inc = 1;     // Reset increasing length
            }
            // If both elements are equal
            else {
                inc = 1;
                dec = 1;
            }
            // Update the maximum length
            maxLength = Math.max(maxLength, Math.max(inc, dec));
        }
        // Return the longest increasing or decreasing length
        return maxLength;
    }
}