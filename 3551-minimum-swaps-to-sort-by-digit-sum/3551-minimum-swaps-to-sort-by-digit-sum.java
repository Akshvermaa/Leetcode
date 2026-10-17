import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] sorted = new Integer[n];

        for (int i = 0; i < n; i++) {
            sorted[i] = nums[i];
        }

        Arrays.sort(sorted, (a, b) -> {
            int sa = digitSum(a);
            int sb = digitSum(b);

            if (sa != sb) {
                return sa - sb;
            }

            return a - b;
        });

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(nums[i], i);
        }

        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != sorted[i]) {
                int j = map.get(sorted[i]);

                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                map.put(nums[j], j);
                map.put(nums[i], i);

                swaps++;
            }
        }

        return swaps;
    }

    private int digitSum(int n) {
        int sum = 0;

        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }
}