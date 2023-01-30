import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    public int solution(int k, int m, int[] score) {
        List<Integer> sortedScores = sortScores(score);
        List<AppleBox> appleBoxes = new ArrayList<>();
        while (sortedScores.size() >= m) {
            appleBoxes.add(makeAppleBox(m, sortedScores));
        }
        if (appleBoxes.isEmpty()) {
            return 0;
        }
        return appleBoxes.stream()
            .map(AppleBox::getScore)
            .mapToInt(Integer::intValue)
            .sum();
    }
    
    private List<Integer> sortScores(int[] score) {
        return Arrays.stream(score)
            .boxed()
            .sorted()
            .collect(Collectors.toList());
    }
    
    private AppleBox makeAppleBox(int extractAppleSize, List<Integer> scores) {
        List<Integer> temp = new ArrayList<>();
        if (scores.size() >= extractAppleSize) {
            for (int i = 0; i < extractAppleSize; ++i) {
                temp.add(scores.remove(scores.size() - 1));
            }
            return new AppleBox(temp);
        }
        throw new IllegalArgumentException("It is Empty");
    }


}

class AppleBox {

    private final List<Integer> apples;

    public AppleBox(List<Integer> apples) {
        this.apples = apples;
    }

    public int getScore() {
        return apples.size() * apples.stream().min(Comparator.comparingInt(o -> o)).orElse(0);
    }
}