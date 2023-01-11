import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    public int solution(String numbers) {
        KeyboardResolver keyboardResolver = new KeyboardResolver(numbers);
        return keyboardResolver.findMinimumCost(4, 6, 0);
    }
}

class KeyboardResolver {

    private static final int INF = 1_000_000_000;
    private final List<Integer> numbers;
    private final int[][][] cache;

    public KeyboardResolver(String numbers) {
        this.numbers = Arrays.stream(numbers.split(""))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        cache = new int[10][10][this.numbers.size() + 1];
        for (int i = 0; i < cache.length; ++i) {
            for (int j = 0; j < cache[0].length; ++j) {
                Arrays.fill(cache[i][j], INF);
            }
        }
    }

    public int findMinimumCost(int leftNumber, int rightNumber, int index) {
        if (leftNumber == rightNumber) {
            return INF;
        }
        if (index == numbers.size()) {
            return 0;
        }
        if (cache[leftNumber][rightNumber][index] != INF) {
            return cache[leftNumber][rightNumber][index];
        }
        int number = numbers.get(index);
        int leftCost = findMinimumCost(number, rightNumber, index + 1)
            + DistanceCalculator.findDistance(leftNumber, number);
        int rightCost = findMinimumCost(leftNumber, number, index + 1)
            + DistanceCalculator.findDistance(rightNumber, number);
        int minCost = Integer.min(leftCost, rightCost);
        return cache[leftNumber][rightNumber][index] = Integer.min(
            cache[leftNumber][rightNumber][index], minCost);

    }
}

class DistanceCalculator {

    // 1 2 3
    // 4 5 6
    // 7 8 9
    //   0
    private static final int[][] weights = new int[][]{
        {1, 7, 6, 7, 5, 4, 5, 3, 2, 3}, // 0
        {7, 1, 2, 4, 2, 3, 5, 4, 5, 6}, // 1
        {6, 2, 1, 2, 3, 2, 3, 5, 4, 5}, // 2
        {7, 4, 2, 1, 5, 3, 2, 6, 5, 4}, // 3
        {5, 2, 3, 5, 1, 2, 4, 2, 3, 5}, // 4
        {4, 3, 2, 3, 2, 1, 2, 3, 2, 3}, // 5
        {5, 5, 3, 2, 4, 2, 1, 5, 3, 2}, // 6
        {3, 4, 5, 6, 2, 3, 5, 1, 2, 4}, // 7
        {2, 5, 4, 5, 3, 2, 3, 2, 1, 2}, // 8
        {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}, // 9
    };

    private DistanceCalculator() {
    }


    public static int findDistance(int sourceNumber, int targetNumber) {
        return weights[sourceNumber][targetNumber];
    }
}