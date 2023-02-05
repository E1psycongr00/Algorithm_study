import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class Solution {
    
    private static final int CONSTANT = 65536;
    
    public int solution(String str1, String str2) {
        MultiSet set1 = new MultiSet(str1);
        MultiSet set2 = new MultiSet(str2);
        if (set1.getSize() == 0 && set2.getSize() == 0) {
            return 1 * CONSTANT;
        }
        MultiSet union = MultiSet.union(set1, set2);
        MultiSet intersection = MultiSet.intersection(set1, set2);
        System.out.println(union);
        System.out.println(intersection);
        return intersection.getSize() * CONSTANT / union.getSize();
    }
}


class MultiSet {

    private final Map<String, Integer> frequency = new HashMap<>();

    public MultiSet(String s) {
        init(s);
    }

    private void init(String s) {
        for (int i = 0; i < s.length() - 1; ++i) {
            String sub = s.substring(i, i + 2);
            if (Character.isAlphabetic(sub.charAt(0)) && Character.isAlphabetic(sub.charAt(1))) {
                String key = sub.toUpperCase();
                frequency.put(key, frequency.getOrDefault(key, 0) + 1);
            }
        }
    }

    public static MultiSet union(MultiSet set1, MultiSet set2) {
        MultiSet multiSet = new MultiSet("");
        for (Entry<String, Integer> entry : set1.frequency.entrySet()) {
            String key = entry.getKey();
            if (set2.frequency.containsKey(key)) {
                multiSet.frequency.put(key, Integer.max(entry.getValue(), set2.frequency.get(key)));
                continue;
            }
            multiSet.frequency.put(key, entry.getValue());
        }
        for (Entry<String, Integer> entry : set2.frequency.entrySet()) {
            String key = entry.getKey();
            if(multiSet.frequency.containsKey(key)) {
                continue;
            }
            multiSet.frequency.put(key, entry.getValue());
        }
        return multiSet;
    }

    public static MultiSet intersection(MultiSet set1, MultiSet set2) {
        MultiSet multiSet = new MultiSet("");
        for (Entry<String, Integer> entry : set1.frequency.entrySet()) {
            String key = entry.getKey();
            if (set2.frequency.containsKey(key)) {
                multiSet.frequency.put(key, Integer.min(entry.getValue(), set2.frequency.get(key)));
            }
        }
        return multiSet;
    }

    public int getSize() {
        return frequency.values().stream().mapToInt(i -> i).sum();
    }

    @Override
    public String toString() {
        return "MultiSet{" +
            "frequency=" + frequency +
            '}';
    }
}