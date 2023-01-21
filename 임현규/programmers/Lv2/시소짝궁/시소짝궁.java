package backjoon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {

    private final Map<Integer, Long> dict = new HashMap<>();
    public long solution(int[] weights) {
        initDict(weights);
    }

    private void initDict(int[] weights) {
        for (int weight : weights) {
            if (dict.containsKey(weight)) {
                dict.put(weight, dict.get(weight) + 1);
                continue;
            }
            dict.put(weight, 0L);
        }
    }
}