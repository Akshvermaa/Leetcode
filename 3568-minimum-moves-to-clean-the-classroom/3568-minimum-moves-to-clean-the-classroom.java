class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0, litterCount = 0;

        int[][] litter = new int[m][n];
        for (int[] row : litter) Arrays.fill(row, -1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    sr = i;
                    sc = j;
                } else if (c == 'L') {
                    litter[i][j] = litterCount++;
                }
            }
        }

        int fullMask = (1 << litterCount) - 1;

        if (fullMask == 0) return 0;

        boolean[][][][] visited =
            new boolean[m][n][1 << litterCount][energy + 1];

        int maxStates = m * n * (1 << litterCount) * (energy + 1);

        int[] qr = new int[maxStates];
        int[] qc = new int[maxStates];
        int[] qm = new int[maxStates];
        int[] qe = new int[maxStates];

        int head = 0, tail = 0;

        visited[sr][sc][0][energy] = true;

        qr[tail] = sr;
        qc[tail] = sc;
        qm[tail] = 0;
        qe[tail++] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (head < tail) {
            int size = tail - head;

            while (size-- > 0) {
                int r = qr[head];
                int c = qc[head];
                int mask = qm[head];
                int e = qe[head++];

                if (mask == fullMask) return moves;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                        continue;

                    if (classroom[nr].charAt(nc) == 'X')
                        continue;

                    if (e == 0)
                        continue;

                    int ne = e - 1;
                    int nm = mask;

                    if (litter[nr][nc] != -1) {
                        nm |= 1 << litter[nr][nc];
                    }

                    if (classroom[nr].charAt(nc) == 'R') {
                        ne = energy;
                    }

                    if (!visited[nr][nc][nm][ne]) {
                        visited[nr][nc][nm][ne] = true;

                        qr[tail] = nr;
                        qc[tail] = nc;
                        qm[tail] = nm;
                        qe[tail++] = ne;
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}