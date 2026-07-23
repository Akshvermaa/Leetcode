class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n=nums.length;
        if(n<3){
            return n;
        }
        return 1<<Integer.toBinaryString(n).length();
    }
}