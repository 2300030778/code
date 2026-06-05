import java.util.*;

class Solution {

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n < 0) return 0;

        String s = String.valueOf(n);
        int len = s.length();

        Map<String, long[]> memo = new HashMap<>();

        long[] res = dfs(0, -1, -1, true, false, s, memo);
        return res[1]; 
    }

    private long[] dfs(int pos, int last1, int last2, boolean tight, boolean started,
                       String s, Map<String, long[]> memo) {

        if (pos == s.length()) {
            return new long[]{started ? 1 : 0, 0};
        }

        String key = pos + "," + last1 + "," + last2 + "," + tight + "," + started;
        if (!tight && memo.containsKey(key)) return memo.get(key);

        int limit = tight ? s.charAt(pos) - '0' : 9;

        long count = 0;
        long wavinessSum = 0;

        for (int d = 0; d <= limit; d++) {

            boolean newStarted = started || d != 0;

            int newLast1 = last1;
            int newLast2 = last2;

            long add = 0;

            if (!newStarted) {
                newLast1 = -1;
                newLast2 = -1;
            } else {
                if (last1 == -1) {
                    newLast1 = d;
                } else if (last2 == -1) {
                    newLast2 = last1;
                    newLast1 = d;
                } else {
                    
                    if ((last2 < last1 && last1 > d) ||
                        (last2 > last1 && last1 < d)) {
                        add = 1;
                    }
                    newLast2 = last1;
                    newLast1 = d;
                }
            }

            long[] next = dfs(
                    pos + 1,
                    newLast1,
                    newLast2,
                    tight && (d == limit),
                    newStarted,
                    s,
                    memo
            );

            count += next[0];
            wavinessSum += next[1] + add * next[0];
        }

        long[] res = new long[]{count, wavinessSum};
        if (!tight) memo.put(key, res);

        return res;
    }
}