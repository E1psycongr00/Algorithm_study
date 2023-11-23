class Solution {
    public int minDistance(String word1, String word2) {
        int N = word1.length();
        int M = word2.length();
        int[][] dp = new int[N+1][M+1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = n == 0 ? m : n;
                    continue;
                }
                if (word1.charAt(n-1) == word2.charAt(m-1)) {
                    dp[n][m] = dp[n-1][m-1];
                } else {
                    dp[n][m] = Math.min(dp[n-1][m-1], Math.min(dp[n-1][m], dp[n][m-1])) + 1;
                }
            }
        }
        return dp[N][M];
    }
}