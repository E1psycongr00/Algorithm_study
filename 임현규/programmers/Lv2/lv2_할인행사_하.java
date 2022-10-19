import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * skip, limit으로 stream을 짯을 때 통과하지 못했다. 그러나 Array를 slice(java에선 copyOfRange)를
 * 수행하고 stream을 진행했더니 통과했다. skip, limit으로 짯을때 엄청나게 오랜 시간이 걸린 이유는 stream은
 * 배열과 같이 인덱스를 타지 못하기 때문이다.
 * stream은 시퀀스 구조이고 순차적으로 순회한다. 즉 skip의 경우 인덱스를 타지 못하고 stream 처음 부분부터 순회하며
 * 아래와 같은 코드에서 skip을 사용시 O(N^2)이 된다. N = 10^5이므로 당연히 시간초과이다.
 * 이를 해결하는 방법은 slice에서 subArray를 생성하면 된다.
 * 
 * List slice 방법: new ArrayList<>(a.subList(0 ,3)); // a는 List 객체
 * Array slice 방법: Arrays.copyOfRange(a, 0, 3);
 */
class Solution {

    public int solution(String[] want, int[] number, String[] discount) {
        HashMap<String, Long> counter = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            counter.put(want[i], (long)number[i]);
        }
        int cnt = 0;
        for (int i = 0; i < discount.length - 10 + 1; i++) {
            String[] sliced = Arrays.copyOfRange(discount, i, i + 10);
            if (counter.equals(Arrays.stream(sliced).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())))) {
                cnt += 1;
            }
        }
        return cnt;
    }
}