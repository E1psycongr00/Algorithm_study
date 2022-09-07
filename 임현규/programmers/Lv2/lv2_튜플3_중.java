import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Solution {

    public int[] solution(String s) {
        Map<Long, Long> frequency = Pattern.compile("\\d+").matcher(s).results()
            .map(MatchResult::group)
            .map(Long::parseLong)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Long> result = frequency.keySet().stream()
            .sorted((o1, o2) -> (int) (frequency.get(o2) - frequency.get(o1))).collect(
                Collectors.toList());

        return result.stream().mapToInt(Long::intValue).toArray();
    }
}