class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n=nums.length;
        Integer[]idx=new Integer[n];
        for(int i=0;i<n;i++){
            idx[i]=i;
        }
        Arrays.sort(idx,(a,b)->Integer.compare(nums[a],nums[b]));
        int[]ans=nums.clone();
        int start=0;
        while(start<n){
            int end=start;
            while(end+1<n&&(long)nums[idx[end+1]]-nums[idx[end]]<=limit){
                end++;
            }
             int size = end - start + 1;
            int[] positions = new int[size];
            int[] values = new int[size];

            for (int i = 0; i < size; i++) {
                positions[i] = idx[start + i];
                values[i] = nums[idx[start + i]];
            }

            Arrays.sort(positions);

            for (int i = 0; i < size; i++) {
                ans[positions[i]] = values[i];
            }

            start = end + 1;
            }
            return ans;
    }
}