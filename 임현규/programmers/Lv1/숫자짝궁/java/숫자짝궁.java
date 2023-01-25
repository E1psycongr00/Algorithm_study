import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {

    public String solution(String X, String Y) {
        Counter leftCounter = new Counter(X);
        Counter rightCounter = new Counter(Y);
        Counter intersection = leftCounter.intersection(rightCounter);
        String elements = intersection.getElements();
        if (Objects.equals(elements, "")) {
            return "-1";
        }
        return elements;
    }
}


class Counter {

    private final Map<String, Integer> frequency = new HashMap<>();

    public Counter(String input) {
        for (int i = 0; i < input.length(); ++i) {
            String key = String.valueOf(input.charAt(i));
            frequency.put(key, frequency.getOrDefault(key, 0) + 1);
        }
    }

    public Counter intersection(Counter other) {
        Map<String, Integer> otherFrequency = other.frequency;
        Set<Entry<String, Integer>> thisEntries = frequency.entrySet();
        Counter newCounter = new Counter("");
        for (Entry<String, Integer> thisEntry : thisEntries) {
            String key = thisEntry.getKey();
            if (otherFrequency.containsKey(key)) {
                newCounter.frequency.put(key, Integer.min(frequency.get(key), otherFrequency.get(key)));
            }
        }
        return newCounter;
    }

    public String getElements() {
        if (isOnlyZero()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        List<Entry<String, Integer>> sortedEntry= frequency.entrySet().stream()
            .sorted((o1, o2) -> -o1.getKey().compareTo(o2.getKey()))
            .collect(Collectors.toList());
        for (Entry<String, Integer> entry : sortedEntry) {
            String key = entry.getKey();
            int counter = entry.getValue();
            sb.append(String.valueOf(key).repeat(Math.max(0, counter)));
        }
        return sb.toString();
    }

    private boolean isOnlyZero() {
        return frequency.size() == 1 && frequency.containsKey("0");
    }

    @Override
    public String toString() {
        return "frequency=" + frequency;
    }
}