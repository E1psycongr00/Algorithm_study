import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue(Comparator.reverseOrder());
        for (int work : works) {
            pq.add(work);
        }
        for (int i = 0; i < n; ++i) {
            if (pq.isEmpty()) {
                continue;
            }
            int number = pq.poll();
            int pushNumber = --number;
            if (pushNumber >= 0) {
                pq.add(pushNumber);
            }
        }
        return pq.stream()
            .mapToLong(Integer::longValue)
            .map(i -> i * i)
            .sum();
    }
}