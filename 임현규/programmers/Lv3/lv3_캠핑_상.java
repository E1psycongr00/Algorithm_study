import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 부분합 + 좌표 압축 문제
 * 좌표 압축이 필요한 이유는 좌표 값이 너무 커서 2차원 부분합을 만들 수 없다. 그래서 좌표 압축을 통해
 * 값의 최대 크기가 n(5000) 이하가 되도록 만든다.
 * 부분합에서 약간의 트릭이 들어간다.
 * 위의 문제의 조건을 만족하는 두 점의 쌍이 필요한데 우리는 점의 갯수를 누적합으로 구했다. 그렇다면
 * 박스 구간 내에 쌍의 갯수를 구하려면
 * S[x2][y2] - S[x1-1][y2] -S[x2][y1-1] + S[x1-1][y1-1] 이 성립한다.
 * 내부에 쌍의 존재 여부는 다음과 같이 구하면 된다.
 * (x1, y1) ~ (x2, y2) 의 내부 가장 큰 사각형 => arr(x1 +1, y1 + 1) ~ arr(x2 -1, y2 - 1)
 * S[x2-1][y2-1] - S[x1+1-1][y2-1] - S[x2-1][y1+1-1] + S[x1+1-1][x2+1-1]
 */
class Solution {

    public int solution(int n, int[][] data) {
        compressCoordinate(n, data);
        int[][] prefixSum = makePrefixSum(n, data);

        int answer = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (isNoSquare(data[i], data[j])) {
                    continue;
                }
                if (isEmptyInSquare(data[i], data[j], prefixSum)) {
                    answer++;
                }
            }
        }
        return answer;
    }

    private boolean isEmptyInSquare(int[] coordinate1, int[] coordinate2, int[][] S) {
        int minX = Math.min(coordinate1[0], coordinate2[0]);
        int minY = Math.min(coordinate1[1], coordinate2[1]);
        int maxX = Math.max(coordinate1[0], coordinate2[0]);
        int maxY = Math.max(coordinate1[1], coordinate2[1]);

        return S[maxX - 1][maxY - 1] - S[minX][maxY - 1] - S[maxX - 1][minY] + S[minX][minY] == 0;
    }

    private void compressCoordinate(int n, int[][] data) {
        // unique한 좌표를 담아둠
        Set<Integer> uniqueX = new HashSet<>();
        Set<Integer> uniqueY = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            uniqueX.add(data[i][0]);
            uniqueY.add(data[i][1]);
        }
        List<Integer> xList = new ArrayList<>(uniqueX);
        List<Integer> yList = new ArrayList<>(uniqueY);
        Collections.sort(xList);
        Collections.sort(yList);

        for (int i = 0; i < n; ++i) {
            int x = xList.indexOf(data[i][0]);
            int y = yList.indexOf(data[i][1]);
            data[i][0] = x;
            data[i][1] = y;
        }
    }

    private int[][] makePrefixSum(int n, int[][] data) {
        int[][] S = new int[n][n];
        for (int i = 0; i < n; ++i) {
            int x = data[i][0];
            int y = data[i][1];
            S[x][y] = 1;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                S[i][j] += S[i][j - 1];
            }
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                S[i][j] += S[i - 1][j];
            }
        }
        return S;
    }

    private boolean isNoSquare(int[] coordinate1, int[] coordinate2) {
        int x1 = coordinate1[0];
        int y1 = coordinate1[1];
        int x2 = coordinate2[0];
        int y2 = coordinate2[1];
        return x1 == x2 || y1 == y2;
    }
}
