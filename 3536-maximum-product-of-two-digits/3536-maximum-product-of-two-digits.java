class Solution {
    public int maxProduct(int n) {
        int[]freq=new int[10];
        while(n>0){
            freq[n%10]++;
            n/=10;
        }
        for(int i=9;i>=0;i--){
            if(freq[i]>=2){
                return i*i;

            }
            if(freq[i]==1){
                for(int j=i-1;j>=0;j--){
                    if(freq[j]>0){
                        return i*j;
                    }
                }
            }
        }
        return 0;
    }
}