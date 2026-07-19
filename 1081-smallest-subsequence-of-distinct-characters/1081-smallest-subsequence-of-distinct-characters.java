class Solution {
    public String smallestSubsequence(String s) {
       int[]lastIndex=new int[26];
       boolean[]visited=new boolean[26];
       for(int i=0;i<s.length();i++){
        lastIndex[s.charAt(i)-'a']=i;
       } 
               Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (visited[ch - 'a']) {
                continue;
            }
            while (!stack.isEmpty() &&
                   stack.peek() > ch &&
                   lastIndex[stack.peek() - 'a'] > i) {
                visited[stack.pop() - 'a'] = false;
            }
            stack.push(ch);
            visited[ch - 'a'] = true;
        }
        StringBuilder ans = new StringBuilder();
        for (char c : stack) {
            ans.append(c);
        }
        return ans.toString();
    }
}