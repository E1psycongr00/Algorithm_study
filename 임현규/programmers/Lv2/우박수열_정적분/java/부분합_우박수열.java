import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public double[] solution(int k, int[][] ranges) {
        List<Integer> wooback = Woobak.woobakSerialize(k);
        List<Double> prefixSumWidth = Integral.getPrefixSumWidth(wooback);
        return Arrays.stream(ranges)
            .mapToDouble(ints -> Integral.getWidth(prefixSumWidth, ints[0], ints[1]))
            .toArray();
    }

}

class Woobak {

    public static List<Integer> woobakSerialize(int firstNumber) {
        List<Integer> result = new ArrayList<>();
        int number = firstNumber;
        result.add(number);
        while (number > 1) {
            number = adjustWoobakRule(number);
            result.add(number);
        }
        return result;
    }

    private static int adjustWoobakRule(int number) {
        if (number % 2 == 0) {
            return number / 2;
        }
        return number * 3 + 1;
    }
}

class Integral {

    public static List<Double> getPrefixSumWidth(List<Integer> sequence) {
        List<Double> result = new ArrayList<>();
        result.add(0.0);
        for (int i = 1; i < sequence.size(); ++i) {
            double width = (double) (sequence.get(i) + sequence.get(i - 1)) / 2;
            result.add(result.get(i - 1) + width);
        }
        return result;
    }

    public static double getWidth(List<Double> prefixSum, int startIndex, int endIndex) {
        endIndex = prefixSum.size() - 1 + endIndex;
        if (startIndex > endIndex) {
            return -1;
        }
        return prefixSum.get(endIndex) - prefixSum.get(startIndex);
    }
}
