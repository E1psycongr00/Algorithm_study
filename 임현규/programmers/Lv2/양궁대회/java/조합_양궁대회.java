import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    public int[] solution(int n, int[] info) {
        Shot appeachShot = new Shot(info);
        List<Shot> lionCases = makeLionCases(n);
        Shot result = getResultLion(lionCases, appeachShot);
        return result == null ? new int[]{-1} : result.getResult();
    }

    private Shot getResultLion(List<Shot> lionsShotCases, Shot appeachShot) {
        int score = 0;
        Shot result = null;
        for (Shot lionShot : lionsShotCases) {
            int scoreDifference = lionShot.calculateScore(appeachShot);
            if (scoreDifference > score) {
                score = scoreDifference;
                result = lionShot;
                continue;
            }
            if (scoreDifference == score && lionShot.isMorePickLowerScore(appeachShot) && score > 0) {
                result = lionShot;
            }
        }
        return result;
    }

    private List<Shot> makeLionCases(int pickNumbers) {
        List<List<Integer>> pickCases = DuplicateCombination.generate(
            Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10), pickNumbers);
        List<Shot> lionShotCases = new ArrayList<>();
        for (List<Integer> picks : pickCases) {
            List<Integer> tempList = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            for (int score : picks) {
                tempList.set(score, tempList.get(score) + 1);
            }
            lionShotCases.add(new Shot(tempList));
        }
        return lionShotCases;
    }
}

class Shot {

    private static final int LOW_SCORE = 0;
    private static final int HIGH_SCORE = 10;
    private final List<Integer> shots;

    public Shot(int[] info) {
        List<Integer> collections = Arrays.stream(info)
            .boxed()
            .collect(Collectors.toList());
        Collections.reverse(collections);
        this.shots = Collections.unmodifiableList(collections);
    }

    public Shot(List<Integer> info) {
        this.shots = new ArrayList<>(info);
    }

    // 상대방이 비기거나 많이 맞추면 상대방이 점수를 가져감
    public int calculateScore(Shot other) {
        int myScore = 0;
        int otherScore = 0;
        List<Integer> otherShots = other.shots;
        for (int score = LOW_SCORE; score <= HIGH_SCORE; ++score) {
            if (shots.get(score) <= otherShots.get(score) && otherShots.get(score) > 0) {
                otherScore += score;
            }
            if (shots.get(score) > otherShots.get(score) && shots.get(score) > 0) {
                myScore += score;
            }
        }
        return myScore - otherScore;
    }

    public boolean isMorePickLowerScore(Shot other) {
        List<Integer> otherShots = other.shots;
        for (int score = LOW_SCORE; score <= HIGH_SCORE; ++score) {
            if (shots.get(score) > other.shots.get(score)) {
                return true;
            }
            if (shots.get(score) < other.shots.get(score)) {
                return false;
            }
        }
        return false;
    }

    public int[] getResult() {
        List<Integer> result = new ArrayList<>(shots);
        Collections.reverse(result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}

class DuplicateCombination {

    public static <T extends Comparable<? super T>> List<List<T>> generate(List<T> items, int r) {
        List<List<T>> list = new ArrayList<>();
        Collections.sort(items);
        backTrack(list, new ArrayList<>(), items, 0, r);
        return list;
    }

    private static <T> void backTrack(List<List<T>> list, List<T> tempList, List<T> items,
        int start, int r) {
        if (r == 0) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < items.size(); ++i) {
            if (i > start && items.get(i).equals(items.get(i - 1))) {
                continue;
            }
            tempList.add(items.get(i));
            backTrack(list, tempList, items, i, r - 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}