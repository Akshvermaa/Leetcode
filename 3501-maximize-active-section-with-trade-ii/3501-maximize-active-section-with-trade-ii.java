class Solution {
    private static class Group {
        int start;
        int length;
        Group(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }
    private static class SparseTable {
        private final int[][] st;
        private final int[] logTable;
        public SparseTable(int[] nums) {
            int n = nums.length;
            int maxLog = 32 - Integer.numberOfLeadingZeros(n);
            st = new int[maxLog][n];
            logTable = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                logTable[i] = logTable[i / 2] + 1;
            }
            System.arraycopy(nums, 0, st[0], 0, n);
            for (int i = 1; i < maxLog; i++) {
                for (int j = 0; j + (1 << i) <= n; j++) {
                    st[i][j] = Math.max(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
                }
            }
        }
        public int query(int l, int r) {
            if (l > r) return 0;
            int i = logTable[r - l + 1];
            return Math.max(st[i][l], st[i][r - (1 << i) + 1]);
        }
    }
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') totalOnes++;
        }
        List<Group> zeroGroups = new ArrayList<>();
        int[] zeroGroupIndex = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    zeroGroups.get(zeroGroups.size() - 1).length++;
                } else {
                    zeroGroups.add(new Group(i, 1));
                }
                zeroGroupIndex[i] = zeroGroups.size() - 1;
            } else {
                zeroGroupIndex[i] = -1;
            }
        }
        int numGroups = zeroGroups.size();
        List<Integer> ans = new ArrayList<>(queries.length);
        if (numGroups == 0) {
            for (int i = 0; i < queries.length; i++) {
                ans.add(totalOnes);
            }
            return ans;
        }
        int[] adjacentSums = new int[Math.max(0, numGroups - 1)];
        for (int i = 0; i < numGroups - 1; i++) {
            adjacentSums[i] = zeroGroups.get(i).length + zeroGroups.get(i + 1).length;
        }
        SparseTable st = numGroups > 1 ? new SparseTable(adjacentSums) : null;

        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            int firstZeroGroupIdx = -1;
            for (int i = l; i <= r; i++) {
                if (zeroGroupIndex[i] != -1) {
                    firstZeroGroupIdx = zeroGroupIndex[i];
                    break;
                }
            }
            int lastZeroGroupIdx = -1;
            for (int i = r; i >= l; i--) {
                if (zeroGroupIndex[i] != -1) {
                    lastZeroGroupIdx = zeroGroupIndex[i];
                    break;
                }
            }
            if (firstZeroGroupIdx == -1) {
                ans.add(totalOnes);
                continue;
            }
            int leftClipLen = l - zeroGroups.get(zeroGroupIndex[l] != -1 ? zeroGroupIndex[l] : firstZeroGroupIdx).start < 0
                    ? zeroGroups.get(firstZeroGroupIdx).length
                    : zeroGroups.get(zeroGroupIndex[l]).length - (l - zeroGroups.get(zeroGroupIndex[l]).start);
            int rightClipLen = zeroGroupIndex[r] != -1
                    ? (r - zeroGroups.get(zeroGroupIndex[r]).start + 1)
                    : zeroGroups.get(lastZeroGroupIdx).length;
            int activeSections = totalOnes;
            if (s.charAt(l) == '0' && s.charAt(r) == '0' && zeroGroupIndex[l] + 1 == zeroGroupIndex[r]) {
                activeSections = Math.max(activeSections, totalOnes + leftClipLen + rightClipLen);
            } else {
                int startGroup = s.charAt(l) == '0' ? zeroGroupIndex[l] + 1 : firstZeroGroupIdx;
                int endGroup = s.charAt(r) == '0' ? zeroGroupIndex[r] - 1 : lastZeroGroupIdx;
                int startAdj = startGroup;
                int endAdj = endGroup - 1;
                if (startAdj <= endAdj && st != null) {
                    activeSections = Math.max(activeSections, totalOnes + st.query(startAdj, endAdj));
                }
                if (s.charAt(l) == '0' && zeroGroupIndex[l] + 1 <= endGroup) {
                    activeSections = Math.max(activeSections, totalOnes + leftClipLen + zeroGroups.get(zeroGroupIndex[l] + 1).length);
                }
                if (s.charAt(r) == '0' && startGroup <= zeroGroupIndex[r] - 1) {
                    activeSections = Math.max(activeSections, totalOnes + rightClipLen + zeroGroups.get(zeroGroupIndex[r] - 1).length);
                }
            }
            ans.add(activeSections);
        }
        return ans;
    }
}