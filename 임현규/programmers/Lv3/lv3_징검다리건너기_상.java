import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 윈도우의 최대값 중 가장 작은 값을 추적하는 슬라이딩 윈도우
 * <p>
 * </p>
 * line 20 ~ 23 까지는 모노토닉 큐의 push 코드. 항상 내림차순으로 deque 를 정렬해준다.
 * 매번 빼서 사용하는 것이 아닌 사이즈 초과시만 dq를 pollFirst 해주는 이유는 모노토닉 큐 상태의 실제 사이즈와
 * window 사이즈는 일치하지 않기 때문이다. 그래서 윈도우 중 큰 값을 얻어올때는 dq[0]를 호출하고 사이즈 유지는
 * q[0]의 인덱스가 index - size 를 초과하면 제거해준다.
 */
class Solution {

    public int solution(int[] stones, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < stones.length; ++i) {
            while (!dq.isEmpty() && stones[dq.getLast()] <= stones[i]) {
                dq.pollLast();
            }
            dq.addLast(i);
            // maintain window size
            if (isExceedSize(dq, k, i)) {
                dq.pollFirst();
            }

            if (i >= k - 1) {
                result = Math.min(result, stones[dq.getFirst()]);
            }
        }

        return result;
    }

    private boolean isExceedSize(Deque<Integer> dq, int k, int index) {
        return dq.getFirst() == index - k;
    }
}