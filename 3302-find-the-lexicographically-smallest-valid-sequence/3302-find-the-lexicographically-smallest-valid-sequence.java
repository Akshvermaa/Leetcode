class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] last = new int[m];
        Arrays.fill(last, -1);
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
        }
        int[] ans = new int[m];
        int index = 0;
        int skip = 0;
        j = 0;
        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j) ||
                (skip == 0 && (j == m - 1 || i < last[j + 1]))) {
                if (word1.charAt(i) != word2.charAt(j)) {
                    skip = 1;
                }
                ans[index++] = i;
                j++;
            }
        }
        if (j != m) {
            return new int[0];
        }
        return ans;
    }
}