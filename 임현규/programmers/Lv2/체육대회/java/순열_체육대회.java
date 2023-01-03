import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {

    public int solution(int[][] ability) {
        List<List<Integer>> pickCases = Permutation.generate(
            IntStream.range(0, ability.length).boxed().collect(Collectors.toList()),
            ability[0].length);

        return pickCases.stream()
            .mapToInt(pick -> calculateScore(ability, pick))
            .max().orElse(-1);
    }

    private int calculateScore(int[][] ability, List<Integer> pick) {
        int score = 0;
        assert ability[0].length == pick.size();
        for (int i = 0; i < pick.size(); ++i) {
            score += ability[pick.get(i)][i];
        }
        return score;
    }
}


class Permutation {

    public static <T extends Comparable<? super T>> List<List<T>> generate(List<T> items, int r) {
        List<List<T>> list = new ArrayList<>();
        Collections.sort(items);
        backTrack(list, new ArrayList<>(), items, r);
        return list;
    }

    private static <T> void backTrack(List<List<T>> list, List<T> tempList, List<T> items, int r) {
        if (tempList.size() == r) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < items.size(); ++i) {
            if (tempList.contains(items.get(i))) {
                continue;
            }
            tempList.add(items.get(i));
            backTrack(list, tempList, items, r);
            tempList.remove(tempList.size() - 1);
        }
    }
}
