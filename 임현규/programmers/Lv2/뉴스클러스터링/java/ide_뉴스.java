import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {

    private static final int CONST = 65536;

    public int solution(String str1, String str2) {
        Map<String, Long> count1 = countString(splitString(str1));
        Map<String, Long> count2 = countString(splitString(str2));
        int intersection = intersect(count1, count2);
        int union = union(count1, count2);
        if (union == 0) {
            return CONST;
        }
        return intersection * CONST / union;
    }

    private int intersect(Map<String, Long> count1, Map<String, Long> count2) {
        int count = 0;
        for (String key : count1.keySet()) {
            if (count2.containsKey(key)) {
                count += Math.min(count1.get(key), count2.get(key));
            }
        }
        return count;
    }

    private int union(Map<String, Long> count1, Map<String, Long> count2) {
        int count = 0;
        Set<String> unionKey = new HashSet<>();
        unionKey.addAll(count1.keySet());
        unionKey.addAll(count2.keySet());
        for (String key : unionKey) {
            count += Math.max(count1.getOrDefault(key, 0L), count2.getOrDefault(key, 0L));
        }
        return count;
    }

    private List<String> splitString(String str) {
        String upper = str.toUpperCase();
        return IntStream.range(0, upper.length() - 1)
            .mapToObj(i -> upper.substring(i, i + 2))
            .filter(s -> s.matches("[A-Z]{2}"))
            .collect(Collectors.toList());
    }

    private Map<String, Long> countString(List<String> list) {
        return list.stream()
            .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }
}