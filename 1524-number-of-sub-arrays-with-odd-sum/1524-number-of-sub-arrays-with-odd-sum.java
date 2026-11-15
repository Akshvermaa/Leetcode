class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD=1000000007;
        //Count of even and odd prefix sums
        int even=1;//Empty prefix sum is even 
        int odd=0;
        //Stores the number of subarrays with odd sum
        int count=0;
        // Current prefix sum parity
        int prefix = 0;
        for (int num : arr) {
            // Update prefix sum parity
            prefix = (prefix + num) % 2;
            if (prefix == 0) {
                // Current prefix is even.
                // Odd subarrays are formed using previous odd prefixes.
                count = (count + odd) % MOD;
                // Increase even prefix count
                even++;
            } 
            else {
                // Current prefix is odd.
                // Odd subarrays are formed using previous even prefixes.
                count = (count + even) % MOD;
                // Increase odd prefix count
                odd++;
            }
        }
        return count;
    }
}