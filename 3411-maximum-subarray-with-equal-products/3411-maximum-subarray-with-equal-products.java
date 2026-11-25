class Solution {
    public int maxLength(int[] nums) {

        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {

            long product = 1;
            long gcd = 0;
            long lcm = 1;

            for (int j = i; j < n; j++) {

                product *= nums[j];

                gcd = findGCD(gcd, nums[j]);

                lcm = findLCM(lcm, nums[j]);

                if (product == gcd * lcm) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    // GCD Function
    private long findGCD(long a, long b) {

        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    // LCM Function
    private long findLCM(long a, long b) {

        return (a / findGCD(a, b)) * b;
    }
}