import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    private static final int NOT_UPDATED_INDEX = -1;

    public int solution(int[] stones, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < stones.length; i++) {
            while (!dq.isEmpty() && stones[dq.getLast()] < stones[i]) {
                dq.removeLast();
            }
            if (!dq.isEmpty() && dq.getFirst() <= i - k) {
                dq.removeFirst();
            }
            dq.addLast(i);
            if (i >= k - 1) {
                answer = Math.min(answer, stones[dq.getFirst()]);
            }
        }
        return answer;
    }
}