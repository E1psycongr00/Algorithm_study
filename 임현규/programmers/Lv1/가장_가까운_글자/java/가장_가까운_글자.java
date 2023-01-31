import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private final Map<Character, Integer> wordIndexTable = new HashMap<>();

    public int[] solution(String s) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            int index = wordIndexTable.getOrDefault(ch, -1);
            if (index == -1) {
                result.add(index);
                wordIndexTable.put(ch, i);
                continue;
            }
            result.add(i - index);
            wordIndexTable.put(ch, i);
        }
        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}