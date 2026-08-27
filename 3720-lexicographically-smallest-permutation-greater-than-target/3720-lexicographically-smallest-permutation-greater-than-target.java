class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        char[] a = s.toCharArray();
        Arrays.sort(a);
        for (int i = n - 1; i >= 0; i--) {
            char[] prefix = target.substring(0, i).toCharArray();
            int[] freq = new int[26];
            for (char c : a) freq[c - 'a']++;
            boolean possible = true;
            for (char c : prefix) {
                if (freq[c - 'a'] == 0) {
                    possible = false;
                    break;
                }
                freq[c - 'a']--;
            }
            if (!possible) continue;
            int x = target.charAt(i) - 'a';
            for (int c = x + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    StringBuilder res = new StringBuilder();
                    res.append(target, 0, i);
                    res.append((char) ('a' + c));
                    freq[c]--;
                    for (int j = 0; j < 26; j++) {
                        while (freq[j] > 0) {
                            res.append((char) ('a' + j));
                            freq[j]--;
                        }
                    }
                    return res.toString();
                }
            }
        }
        return "";
    }
}