
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 20;
    private static final List<Score> scores = new ArrayList<>();


    static {
        for (int i = MIN_VALUE; i <= MAX_VALUE; ++i) {
            scores.add(new Score(i, 1));
            scores.add(new Score(i * 2, 0));
            scores.add(new Score(i * 3, 0));
        }
        scores.add(new Score(50, 1));
    }

    public int[] solution(int target) {
        for (int i = 1; i <= target; ++i) {
            process(i);
        }
        return Cache.getData(target);
    }

    private void process(int target) {
        for (Score score : scores) {
            int beforeTarget = target - score.getScore();
            updateCache(beforeTarget, target, score);
        }
    }

    private void updateCache(int beforeTarget, int target, Score score) {
        if (beforeTarget < 0) {
            return;
        }
        int newUseDart = Cache.getUseDart(beforeTarget) + 1;
        int newUseBoolOrSingle = Cache.getUseBoolOrSingle(beforeTarget) + score.getUseBoolOrSingle();
        if (newUseDart < Cache.getUseDart(target)) {
            Cache.save(target, newUseDart, newUseBoolOrSingle);
        }
        if (newUseDart == Cache.getUseDart(target)
            && newUseBoolOrSingle > Cache.getUseBoolOrSingle(target)) {
            Cache.save(target, newUseDart, newUseBoolOrSingle);
        }
    }
}

class Score {

    private final int score;
    private final int useBoolOrSingle;

    public Score(int score, int useBoolOrSingle) {
        this.score = score;
        this.useBoolOrSingle = useBoolOrSingle;
    }

    public int getScore() {
        return score;
    }

    public int getUseBoolOrSingle() {
        return useBoolOrSingle;
    }
}

class Cache {

    private static final int MAX_TARGET = 100_000;
    private static final int[] useDartCount = new int[MAX_TARGET + 1];
    private static final int[] useBoolOrSingleCount = new int[MAX_TARGET + 1];

    static {
        Arrays.fill(useDartCount, Integer.MAX_VALUE);
        useDartCount[0] = 0;
    }
    public static int getUseDart(int index) {
        return useDartCount[index];
    }

    public static int getUseBoolOrSingle(int index) {
        return useBoolOrSingleCount[index];
    }

    public static void save(int target, int useDart, int useBoolOrSingle) {
        useDartCount[target] = useDart;
        useBoolOrSingleCount[target] = useBoolOrSingle;
    }

    public static int[] getData(int index) {
        return new int[]{useDartCount[index], useBoolOrSingleCount[index]};
    }
}