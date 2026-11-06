class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs,(a,b)->a[1]-b[1]);
        int m=queries.length;
        int[][] q=new int [m][2];
        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        Arrays.sort(q, (a, b) -> a[0] - b[0]);

        int[] ans = new int[m];

        int[] count = new int[n + 1];

        int left = 0, right = 0;
        int activeServers = 0;

        for (int i = 0; i < m; i++) {

            int start = q[i][0] - x;
            int end = q[i][0];

            // Add logs within the current time window
            while (right < logs.length && logs[right][1] <= end) {

                int server = logs[right][0];

                if (count[server] == 0) {
                    activeServers++;
                }

                count[server]++;
                right++;
            }

            // Remove logs outside the current time window
            while (left < right && logs[left][1] < start) {

                int server = logs[left][0];

                count[server]--;

                if (count[server] == 0) {
                    activeServers--;
                }

                left++;
            }

            ans[q[i][1]] = n - activeServers;
        }

        return ans;
    }
}