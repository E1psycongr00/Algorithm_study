import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (x, y) -> Integer.compare(x[1], y[1]));
        int nowEnd = -30000 - 1;
        int count = 0;
        for (int[] interval : routes) {
            if (interval[0] <= nowEnd) {
                continue;
            }
            nowEnd = interval[1];
            count++;
        }
        return count;
    }
}