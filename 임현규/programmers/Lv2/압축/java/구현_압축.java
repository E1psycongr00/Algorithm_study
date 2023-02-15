import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String msg) {
        LZWCompression lzwCompression = new LZWCompression();
        return lzwCompression.compress(msg).stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}

class LZWCompression {
    private final Map<String, Integer> dictionary;

    public LZWCompression() {
        dictionary = new HashMap<>();
        initDictionary();
    }

    public List<Integer> compress(String word) {
        int begin = 0;
        int maxIndex = 26;
        int updateIndex = -1;
        List<Integer> updateIndexes = new ArrayList<>();
        while (begin < word.length()) {
            int end = begin;
            String substring = word.substring(begin, end + 1);
            while (dictionary.containsKey(substring)) {
                end++;
                updateIndex = dictionary.get(substring);
                if (end >= word.length()) {
                    break;
                }
                substring = word.substring(begin, end + 1);
            }
            begin = end;
            maxIndex++;
            dictionary.put(substring, maxIndex);
            updateIndexes.add(updateIndex);
        }
        return updateIndexes;
    }

    private void initDictionary() {
        for (int i = 1; i <= 26; ++i) {
            char alphabet = (char) ('A' + (i - 1));
            dictionary.put(String.valueOf(alphabet), i);
        }
    }
}