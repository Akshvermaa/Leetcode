class Solution {
    private static final long LIMIT=1_000_001L;
    public String smallestPalindrome(String s, int k) {
        int[]freq=new int[26];
        for(char c:s.toCharArray())freq[c-'a']++;
        int[]half=new int[26];
        String mid="";
        int len=0;
        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            len += half[i];
            if ((freq[i] & 1) == 1) mid = String.valueOf((char) ('a' + i));
        }
        long total = countWays(half, len);
        if (total < k) return "";
        StringBuilder left = new StringBuilder();
        while (len > 0) {
            for (int i = 0; i < 26; i++) {
                if (half[i] == 0) continue;
                half[i]--;
                long cnt = countWays(half, len - 1);
                if (cnt >= k) {
                    left.append((char) ('a' + i));
                    len--;
                    break;
                } else {
                    k -= cnt;
                    half[i]++;
                }
            }
        }
        StringBuilder ans = new StringBuilder(left);
        ans.append(mid);
        ans.append(left.reverse());

        return ans.toString();
    }
    private long countWays(int[] half, int total) {
        long res = 1;
        int used = 0;
        for (int c : half) {
            if (c == 0) continue;
            res = Math.min(LIMIT, res * combLimited(used + c, c));
            if (res >= LIMIT) return LIMIT;
            used += c;
        }
        return res;
    }
    private long combLimited(int n, int r) {
        if (r > n - r) r = n - r;
        long res = 1;
        for (int i = 1; i <= r; i++) {
            long a = n - r + i;
            long b = i;
            long g = gcd(a, b);
            a /= g;
            b /= g;
            long g2 = gcd(res, b);
            res /= g2;
            b /= g2;
            if (res > LIMIT / a) return LIMIT;
            res *= a;
            res /= b;
            if (res >= LIMIT) return LIMIT;
        }
        return res;
    }
    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}