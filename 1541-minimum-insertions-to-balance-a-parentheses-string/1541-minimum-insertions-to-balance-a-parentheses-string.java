class Solution {
    public int minInsertions(String s) {
        int insertions=0;
        int open=0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                open++;
            } else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    insertions++;
                }
                if (open > 0) {
                    open--;
                } else {
                    insertions++;
                }
            }
        }
        return insertions + open *2;
    }
}