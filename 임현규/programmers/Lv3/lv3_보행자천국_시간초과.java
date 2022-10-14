/**
 * 너무 시간이 빡세게 구성되어 있는 것 같다.
 * DFS + CACHE를 활용한 하향식 DP 풀이로는 시간 초과가 난다.
 */
class Solution {

    private static final int MOD = 20170805;

    // Direction
    private static final int RIGHT = 0;
    private static final int DOWN = 1;
    private static final int[] dy = new int[] { 0, 1 };
    private static final int[] dx = new int[] { 1, 0 };

    private int[][][] cache;

    public int solution(int m, int n, int[][] cityMap) {
        cache = new int[m][n][2];
        return optimalDfs(0, 0, RIGHT, m, n, cityMap);
    }

    private int optimalDfs(int y, int x, int direction, int m, int n, int[][] cityMap) {
        if (y == m - 1 && x == n - 1) {
            return 1;
        }
        if (cache[y][x][direction] > 0) {
            return cache[y][x][direction];
        }
        if (cityMap[y][x] == 0) {
            for (int dir = 0; dir < 2; ++dir) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (satisfyCondition(ny, nx, cityMap)) {
                    cache[y][x][direction] += optimalDfs(ny, nx, dir, m, n, cityMap);
                    cache[y][x][direction] %= MOD;
                }
            }
        } else if (cityMap[y][x] == 2) {
            int ny = y + dy[direction];
            int nx = x + dx[direction];
            if (satisfyCondition(ny, nx, cityMap)) {
                cache[y][x][direction] += optimalDfs(ny, nx, direction, m, n, cityMap);
                cache[y][x][direction] %= MOD;

            }
        }

        return cache[y][x][direction];
    }

    private boolean satisfyCondition(int ny, int nx, int[][] cityMap) {
        int m = cityMap.length;
        int n = cityMap[0].length;
        return 0 <= ny && ny < m && 0 <= nx && nx < n;
    }

}