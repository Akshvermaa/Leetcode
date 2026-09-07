class Solution {
    public int distinctSubseqII(String s) {
        long[]dp=new long[26];
        long total=0;
        long mod=1000000007;
        for(char c:s.toCharArray()){
            int i=c-'a';
            long newSubseq=(total+1)%mod;
            total=(total+newSubseq-dp[i]+mod)%mod;
            dp[i]=newSubseq;
        }
        return(int)total;
    }
}