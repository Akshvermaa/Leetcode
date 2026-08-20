class Solution {
    public int[] resultArray(int[] nums) {
        int n=nums.length;
        int[]arr1=new int[n];
        int[]arr2=new int[n];
        int i1=1,i2=1;
        arr1[0]=nums[0];
        arr2[0]=nums[1];
        for (int i = 2; i < n; i++) {
            if (arr1[i1 - 1] > arr2[i2 - 1]) {
                arr1[i1++] = nums[i];
            } else {
                arr2[i2++] = nums[i];
            }
        }
        int[] result = new int[n];
        for (int i = 0; i < i1; i++) {
            result[i] = arr1[i];
        }
        for (int i = 0; i < i2; i++) {
            result[i1 + i] = arr2[i];
        }
        return result;
    }
}