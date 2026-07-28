class Solution {
    public String smallestPalindrome(String s) {
        int[]freq=new int[26];
        for(char c:s.toCharArray()){
            freq[c-'a']++;
        }
        StringBuilder first=new StringBuilder();
        String mid="";
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < freq[i] / 2; j++) {
                first.append((char) ('a' + i));
            }
            if (freq[i] % 2 == 1) {
                mid = String.valueOf((char) ('a' + i));
            }
        }
        StringBuilder second = new StringBuilder(first).reverse();
        return first.toString() + mid + second.toString();
    }
}