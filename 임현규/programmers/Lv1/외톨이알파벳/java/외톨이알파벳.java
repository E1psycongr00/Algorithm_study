import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class Solution {

    private final Map<Character, Boolean> soloAlphabetCache = new HashMap<>();

    public String solution(String input_string) {
        for (int i = 0; i < input_string.length(); ++i) {
            Character ch = input_string.charAt(i);
            if (!soloAlphabetCache.containsKey(ch)) {
                soloAlphabetCache.put(ch, false);
                continue;
            }
            if (soloAlphabetCache.containsKey(ch) && i > 0 && input_string.charAt(i-1) != ch) {
                soloAlphabetCache.put(ch, true);
            }
        }
        String result = getResult();
        return result.isEmpty() ? "N" : result;
    }

    private String getResult() {
        return soloAlphabetCache.entrySet().stream()
            .filter(Entry::getValue)
            .map(Entry::getKey)
            .map(String::valueOf)
            .sorted()
            .collect(Collectors.joining(""));
    }
}
