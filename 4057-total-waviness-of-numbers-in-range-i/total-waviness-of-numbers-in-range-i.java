import java.util.Arrays;

class Solution {
    private String s;
    private long[][][][][] memoCnt;
    private long[][][][][] memoSum;

    public int totalWaviness(int num1, int num2) {
        return (int) (calc(num2) - calc(num1 - 1));
    }

    private long calc(int n) {
        if (n <= 0) return 0;

        s = String.valueOf(n);
        int m = s.length();

        memoCnt = new long[m + 1][2][3][11][11];
        memoSum = new long[m + 1][2][3][11][11];

        for (long[][][][] a : memoCnt)
            for (long[][][] b : a)
                for (long[][] c : b)
                    for (long[] d : c)
                        Arrays.fill(d, -1);

        dfs(0, 1, 0, 10, 10);
        return memoSum[0][1][0][10][10];
    }

    private long[] dfs(int pos, int tight, int len, int last2, int last1) {
        if (pos == s.length()) {
            return new long[]{1, 0}; 
        }

        if (memoCnt[pos][tight][len][last2][last1] != -1) {
            return new long[]{
                memoCnt[pos][tight][len][last2][last1],
                memoSum[pos][tight][len][last2][last1]
            };
        }

        int limit = (tight == 1) ? s.charAt(pos) - '0' : 9;

        long totalCnt = 0;
        long totalSum = 0;

        for (int d = 0; d <= limit; d++) {
            int ntight = (tight == 1 && d == limit) ? 1 : 0;

            if (len == 0 && d == 0) {
                long[] nxt = dfs(pos + 1, ntight, 0, 10, 10);
                totalCnt += nxt[0];
                totalSum += nxt[1];
            } else {
                int add = 0;

                if (len >= 2) {
                    if ((last1 > last2 && last1 > d) ||
                        (last1 < last2 && last1 < d)) {
                        add = 1;
                    }
                }

                int nlen = Math.min(2, len + 1);

                int nlast2, nlast1;
                if (len == 0) {
                    nlast2 = 10;
                    nlast1 = d;
                } else if (len == 1) {
                    nlast2 = last1;
                    nlast1 = d;
                } else {
                    nlast2 = last1;
                    nlast1 = d;
                }

                long[] nxt = dfs(pos + 1, ntight, nlen, nlast2, nlast1);

                totalCnt += nxt[0];
                totalSum += nxt[1] + (long) add * nxt[0];
            }
        }

        memoCnt[pos][tight][len][last2][last1] = totalCnt;
        memoSum[pos][tight][len][last2][last1] = totalSum;

        return new long[]{totalCnt, totalSum};
    }
}