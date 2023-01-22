package backjoon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {

    private final Map<Integer, Long> dict = new HashMap<>();
    private long count = 0;

    public long solution(int[] weights) {
        initDict(weights);
        int[] ints = Arrays.stream(weights).distinct().toArray();
        for (int weight : ints) {
            update1x(weight);
            update2_1x(weight);
            update3_2x(weight);
            update4_3x(weight);
        }
        return count;
    }

    private void initDict(int[] weights) {
        for (int weight : weights) {
            dict.put(weight, dict.getOrDefault(weight, 0L) + 1);
        }
    }

    private void update1x(int weight) {
        if (dict.containsKey(weight) && dict.get(weight) > 1) {
            long cnt = dict.get(weight);
            count += cnt * (cnt - 1) / 2;
        }
    }

    private void update2_1x(int weight) {
        int newWeight = weight * 2;
        long firstCnt = dict.getOrDefault(weight, 0L);
        long secondCnt = dict.getOrDefault(newWeight, 0L);
        if (firstCnt > 0 && secondCnt > 0) {
            count += firstCnt * secondCnt;
        }
    }

    private void update3_2x(int weight) {
        double newWeight = (double) weight * 3 / 2;
        if (newWeight == (int) newWeight) {
            long firstCnt = dict.getOrDefault(weight, 0L);
            long secondCnt = dict.getOrDefault((int) newWeight, 0L);
            if (firstCnt > 0 && secondCnt > 0) {
                count += firstCnt * secondCnt;
            }
        }

    }

    private void update4_3x(int weight) {
        double newWeight = (double) weight * 4 / 3;
        if (newWeight == (int) newWeight) {
            long firstCnt = dict.getOrDefault(weight, 0L);
            long secondCnt = dict.getOrDefault((int) newWeight, 0L);
            if (firstCnt > 0 && secondCnt > 0) {
                count += firstCnt * secondCnt;
            }
        }
    }
}