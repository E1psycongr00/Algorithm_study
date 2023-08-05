import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {

    public int solution(int[] toppings) {
        Map<Integer, Long> frequency = Arrays.stream(toppings).boxed()
            .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        Map<Integer, Long> brother = new HashMap<>();
        int cnt = 0;

        for (int topping : toppings) {
            frequency.put(topping, frequency.get(topping) - 1);
            if (frequency.get(topping) == 0) {
                frequency.remove(topping);
            }
            brother.put(topping, brother.getOrDefault(topping, 0L) + 1);
            if (frequency.size() == brother.size()) {
                cnt++;
            }
        }
        return cnt;
    }
}