import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int cnt = 0;
        int begin = 0;
        for (int station : stations) {
            int left = station - w;
            int right = station + w;
            int space = left - begin - 1;
            if (space > 0) {
                cnt += Math.ceil((double)space / (2 * w + 1));
            }
            begin = right;
        }
        if (begin < n) {
            int space = n - begin;
            if (space > 0) {
                cnt += Math.ceil((double)space / (2 * w + 1));
            }
        }
        return cnt;
    }
}