import java.util.stream.IntStream;

class Solution {

    public int solution(int[] arrayA, int[] arrayB) {
        int maxA = getMaxValue(arrayA, arrayB);
        int maxB = getMaxValue(arrayB, arrayA);
        return Integer.max(maxA, maxB);
    }

    private int getMaxValue(int[] arrayA, int[] arrayB) {
        int gcdA = Gcd.calculateAll(arrayA);
        if (checkNotDivide(arrayB, gcdA)) {
            return gcdA;
        }
        return 0;
    }

    private boolean checkNotDivide(int[] array, int input) {
        return IntStream.range(0, array.length).allMatch(i -> array[i] % input != 0);
    }
}

class Gcd {

    public static int calculate(int a, int b) {
        if (a == 0) {
            return b;
        }
        return calculate(b % a, a);
    }

    public static int calculateAll(int[] array) {
        if (array.length == 1) {
            return array[0];
        }
        int result = calculate(array[0], array[1]);
        for (int i = 2; i < array.length; ++i) {
            result = calculate(result, array[i]);
        }
        return result;
    }
}