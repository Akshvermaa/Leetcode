class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Set<String> result = new HashSet<>();

        int left = 0;
        int right = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        backtrack(s, 0, 0, 0, left, right, new StringBuilder(), result);

        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index, int open, int close,
                            int removeOpen, int removeClose,
                            StringBuilder current, Set<String> result) {

        if (index == s.length()) {
            if (open == close && removeOpen == 0 && removeClose == 0) {
                result.add(current.toString());
            }
            return;
        }

        char c = s.charAt(index);

        if (c == '(' && removeOpen > 0) {
            backtrack(s, index + 1, open, close,
                    removeOpen - 1, removeClose, current, result);
        }

        if (c == ')' && removeClose > 0) {
            backtrack(s, index + 1, open, close,
                    removeOpen, removeClose - 1, current, result);
        }

        current.append(c);

        if (c == '(') {
            backtrack(s, index + 1, open + 1, close,
                    removeOpen, removeClose, current, result);
        } else if (c == ')') {
            if (open > close) {
                backtrack(s, index + 1, open, close + 1,
                        removeOpen, removeClose, current, result);
            }
        } else {
            backtrack(s, index + 1, open, close,
                    removeOpen, removeClose, current, result);
        }

        current.deleteCharAt(current.length() - 1);
    }
}