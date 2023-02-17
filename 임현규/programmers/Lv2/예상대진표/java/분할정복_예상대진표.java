import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    private final List<Integer> answer = new ArrayList<>();

    public int solution(int n, int a, int b) {
        findDepth(0, n-1, a - 1, b - 1, (int) (Math.log(n) / Math.log(2)));

        return Collections.min(answer);
    }

    private void findDepth(int low, int hi, int a, int b, int depth) {
        if (depth == 0) {
            return;
        }
        if (isInside(low, hi, a) && isInside(low, hi, b)) {
            answer.add(depth);
        }
        int mid = (low + hi) / 2;
        findDepth(low, mid, a, b, depth - 1);
        findDepth(mid + 1, hi, a, b, depth - 1);
    }
    private boolean isInside(int low, int hi, int number) {
        return low <= number && number <= hi;
    }
}