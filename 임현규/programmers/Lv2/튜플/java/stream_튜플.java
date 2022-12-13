package backjoon;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {

    public int[] solution(String input) {
        Map<Integer, Long> counts = Stream.of(input.replaceAll("[}{]", "").split(","))
            .map(Integer::parseInt)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return counts.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .map(Map.Entry::getKey)
            .mapToInt(x -> x).toArray();
    }
}