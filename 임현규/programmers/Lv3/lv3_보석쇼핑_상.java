
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {

    public int[] solution(String[] gems) {
        int kinds = new HashSet<String>(List.of(gems)).size();
        int left = 0;
        int right = 0;
        int[] answer = new int[] {-1, 100000000};
        Counter cache = new Counter(new String[]{gems[0]});
        while (left <= right && right < gems.length) {
            if (cache.getSize() < kinds) {
                right++;
                if (right >= gems.length) {
                    break;
                }
                cache.add(gems[right]);
            }
            else {
                if (right - left < answer[1] - answer[0]) {
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }
                cache.sub(gems[left]);
                left++;
            }
        }
        return answer;
    }
}

class Counter {

    private final Map<String, Long> map;

    public Counter(String[] list) {
        map = Arrays.stream(list)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public int getSize() {
        return map.size();
    }

    public void add(String item) {
        map.put(item, map.getOrDefault(item, 0L) + 1);
    }

    public void sub(String item) {
        map.put(item, map.getOrDefault(item, 0L) - 1);
        if (map.get(item) <= 0) {
            map.remove(item);
        }
    }

    @Override
    public String toString() {
        return "Counter{" +
            "map=" + map +
            '}';
    }
}