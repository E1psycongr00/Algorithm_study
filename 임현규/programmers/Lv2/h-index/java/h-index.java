import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    public int solution(int[] citations) {
        List<Integer> reversedOrder = Arrays.stream(citations)
            .boxed()
            .sorted(Collections.reverseOrder())
            .collect(Collectors.toList());
        int h = reversedOrder.size();
        while (reversedOrder.get(h - 1) < h && h > 1) {
            h--;
        }
        if (h == 1) {
            if(reversedOrder.get(h) == 0) {
                return 0;
            }
        }
        return h;
    }
}