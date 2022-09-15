import java.util.Arrays;

/**
 * 플루이드 와셜 알고리즘 문제
 * 문제 조건이 까다로울 수도 있지만 요약하면 중간 경우한번 하고 경유지 부터 목적지까지 다 합한 경우 중 최소값
 * 모든 경우의 최소 거리를 캐시해서 사용하는 플루이드 와셜 알고리즘이 적합.
 */
class Solution {

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] matrix = initFloydMatrix(n, fares);

        for (int k = 1; k <= n; ++k) {
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int k = 1; k <= n; ++k) {
            answer = Math.min(answer, matrix[s][k] + matrix[k][a] + matrix[k][b]);
        }

        return answer;
    }

    private int[][] initFloydMatrix(int n, int[][] fares) {
        int[][] matrix = new int[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(matrix[i], 1_000_000);
        }
        for (int i = 1; i <= n; ++i) {
            matrix[i][i] = 0;
        }

        for (int[] edge : fares) {
            matrix[edge[0]][edge[1]] = edge[2];
            matrix[edge[1]][edge[0]] = edge[2];
        }
        return matrix;
    }
}