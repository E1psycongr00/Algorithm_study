import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {

    public int solution(int k, int[] tangerine) {
        PriorityQueue<Long> pq = makeFrequencyCountMaxHeap(tangerine);
        int result = 0;
        while (k > 0) {
            int count = Objects.requireNonNull(pq.poll()).intValue();
            k -= count;
            result += 1;
        }
        return result;
    }

    private Map<Integer, Long> makeFrequency(int[] tangerine) {
        return Arrays.stream(tangerine).boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private PriorityQueue<Long> makeFrequencyCountMaxHeap(int[] tangerine) {
        PriorityQueue<Long> pq = new PriorityQueue<>(
            Comparator.comparing(value -> value, Comparator.reverseOrder()));
        pq.addAll(makeFrequency(tangerine).values());
        return pq;
    }
}