class Solution {
    int i = 0;
    public List<String> braceExpansionII(String expression) {
        Set<String> set = parse(expression);
        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans);
        return ans;
    }
    Set<String> parse(String s) {
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");
        while (i < s.length() && s.charAt(i) != '}') {
            char ch = s.charAt(i);
            if (ch == ',') {
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                i++;
            } else {
                Set<String> next;
                if (ch == '{') {
                    i++;
                    next = parse(s);
                    i++;
                } else {
                    next = new HashSet<>();
                    next.add(String.valueOf(ch));
                    i++;
                }
                Set<String> temp = new HashSet<>();
                for (String a : current) {
                    for (String b : next) {
                        temp.add(a + b);
                    }
                }
                current = temp;
            }
        }
        result.addAll(current);
        return result;
    }
}