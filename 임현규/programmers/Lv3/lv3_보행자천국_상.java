/**
 * 처음에 재귀 DP를 사용하려니 시간 초과가 떠서 반복문 DP를 사용했다.
 * 재귀 DP는 직관적이여서 생각하기 쉬웠는데 반복 DP로 바꾸려니 조금 까다로웠다.
 * 기본적으로 DP[y][x] = DP[y-1][x] + DP[y][x-1] 이 성립한다. 그러나 
 * cityMap[y][x] == 2 일 때는 방향을 고려해줘야 하기 때문에 3차원 DP를 사용해야 했다.
 * 3차원 DP에서의 규칙은 다음과 같다. 
 * DP[y][x][direction] 으로 좌표 (y,x) 에 대해서 방향 성분을 가졌을 때 값이다.
 * 고로 Dp[y][x][direction] 은 2가지 조건에 따라 정의 할 수 있다.
 * 
 * Dp[y][x][D] = sum(Dp[by][bx]) if Dp[by][bx] == 0
 * DP[y][x][D] = Dp[by][bx][D] if Dp[by][bx] == 2
 * 
 * 방향의 이전 좌표가 0인 경우 모든 방향의 횟수를 모두 더해서 가져옴
 * 2인 경우 해당 좌표의 방향만 가져옴(직선 움직임만 수행하기 때문) 
 */
class Solution {

    private static final int MOD = 20170805;

    private static final int DOWN = 0;
    private static final int RIGHT = 1;

    private static final int[] dy = new int[] { 1, 0 };
    private static final int[] dx = new int[] { 0, 1 };

    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2];

        dp[0][0][DOWN] = dp[0][0][RIGHT] = 1;

        for (int i = 1; i < m; ++i) {
            if (cityMap[i][0] != 1) {
                dp[i][0][DOWN] = dp[i - 1][0][DOWN];
            }
        }

        for (int j = 1; j < n; ++j) {
            if (cityMap[0][j] != 1) {
                dp[0][j][RIGHT] = dp[0][j - 1][RIGHT];
            }

        }

        for (int y = 1; y < m; ++y) {
            for (int x = 1; x < n; ++x) {
                for (int k = 0; k < 2; ++k) {
                    int by = y - dy[k];
                    int bx = x - dx[k];
                    if (cityMap[by][bx] == 0) {
                        dp[y][x][k] = (dp[by][bx][DOWN] + dp[by][bx][RIGHT]) % MOD;
                    } else if (cityMap[by][bx] == 2) {
                        dp[y][x][k] = dp[by][bx][k];
                    }
                }
            }
        }
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;

    }
}