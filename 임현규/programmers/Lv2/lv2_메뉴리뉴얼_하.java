import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        for (int r : course) {
            List<String> temp = new ArrayList<>();
            for (String order : orders) {
                String ordered = Arrays.stream(order.split("")).sorted()
                    .collect(Collectors.joining());
                if (ordered.length() >= r) {
                    Combinations.combinations(ordered, new char[r], 0, 0, r, temp);
                }
            }
            if (temp.isEmpty()) continue;
            
            Map<String, Long> frequency = temp.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            int maxValue = frequency.values().stream().mapToInt(Long::intValue).max()
                .orElseThrow();

            frequency.entrySet().stream()
                .filter(entry -> entry.getValue() > 1 && entry.getValue() == maxValue)
                .map(Entry::getKey).forEach(key -> answer.add(key));
        }
        return answer.stream().sorted().toArray(String[]::new);
    }
}


class Combinations {

    public static void combinations(String s, char[] picked, int pos, int step, int r,
        List<String> answer) {
        if (step == r) {
            answer.add(String.valueOf(picked));
            return;
        }

        for (int i = pos; i < s.length(); ++i) {
            picked[step] = s.charAt(i);
            combinations(s, picked, i + 1, step + 1, r, answer);
        }
    }

}