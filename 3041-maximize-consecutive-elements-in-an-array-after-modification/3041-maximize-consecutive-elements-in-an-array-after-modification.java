//T.C : O(nlogn)
//S.C : O(n)
class Solution {
    int n;
    int[][] dp;

    // First index >= start where nums[index] == target, or -1 if none
    private int findMethod(int[] nums, int start, int target) {
        int lo = start, hi = n; // search in [start, n)
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        if (lo < n && nums[lo] == target) {
            return lo;
        }

        return -1;
    }

    public int maxSelectedElements(int[] nums) {
        n = nums.length;

        Arrays.sort(nums);

        dp = new int[n + 1][2];

        int result = 1;

        for (int i = n - 1; i >= 0; i--) { //O(n * log(n))
            for (int inc = 0; inc <= 1; inc++) {

                int currVal = nums[i] + inc;
                int len = 1;

                //1st Option = find (currVal+1)
                int j = findMethod(nums, i + 1, currVal + 1);
                if (j != -1) {
                    len = Math.max(len, 1 + dp[j][0]);
                }

                //2nd Option = find (currVal) and then inc by +1
                j = findMethod(nums, i + 1, currVal);
                if (j != -1) {
                    len = Math.max(len, 1 + dp[j][1]);
                }

                dp[i][inc] = len;
                result = Math.max(result, len);
            }
        }

        return result;
    }
}