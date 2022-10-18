import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  stream으로 slicing을 하면 시간 초과난다. 
 * 그 이유는 stream은 객체 생성 비용이 비싼데 for 문으로 slice 객체를 계속 생성하기 때문이다.
 * 
 */
class Solution {

    public int solution(int[] elements) {
        int n = elements.length;
        List<Integer> cycle = Stream.concat(Arrays.stream(elements).boxed(),
            Arrays.stream(elements).boxed()).collect(
            Collectors.toList());
        HashSet<Integer> answer = new HashSet<>();
        for (int k = 1; k <= n; ++k) {
            for (int i = 0; i < n; ++i) {
                answer.add(sumOfSubList(cycle, i, k));
            }
        }
        return answer.size();
    }
    private static int sumOfSubList(List<Integer> list, int start, int k) {
        int sum = 0;
        for (int i = start; i < start + k; ++i) {
            sum += list.get(i);
        }
        return sum;
    }
}