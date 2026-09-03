class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = nums1[0];
        boolean odd = false;
        boolean even = false;
        for (int num : nums1) {
            min = Math.min(min, num);
            if (num % 2 == 0) {
                even = true;
            } else {
                odd = true;
            }
        }
        if (!odd || !even) {
            return true;
        }
        return min % 2 == 1;
    }
}