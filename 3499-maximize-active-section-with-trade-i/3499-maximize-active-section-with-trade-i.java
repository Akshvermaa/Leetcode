class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int ones = 0, maxsum = 0, prevrun = -1;
        int i=0;
        while(i<n) {
            if(s.charAt(i)=='1')
            {
                ones++;
                i++;
            } else {
                int curr = 0;
                while(i<n && s.charAt(i)=='0') {
                    curr++;
                    i++;
                }
                if(prevrun > 0)
                    maxsum = Math.max(maxsum, prevrun + curr);
                prevrun = curr;
            }
        }
        return ones + maxsum;
    }
}