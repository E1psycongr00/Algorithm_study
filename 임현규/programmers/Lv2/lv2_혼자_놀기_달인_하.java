import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {

    public int solution(int[] cards) {
        List<Integer> cardList = Arrays.stream(cards).boxed().collect(Collectors.toList());
        boolean[] visited = new boolean[cards.length + 1];
        int[] boxes = IntStream.rangeClosed(1, cards.length + 1).toArray();
        List<Integer> answerList = new ArrayList<>();
        for (int i = 0; i < cardList.size(); ++i) {
            int number = cardList.get(i);
            if (!visited[number]) {
                answerList.add(dfs(boxes, cardList, visited, number));
            }
        }
        answerList.sort((o1, o2) -> o2 - o1);
        if (answerList.size() == 1) {
            return 0;
        }
        return answerList.get(0) * answerList.get(1);
    }

    private int dfs(int[] boxes, List<Integer> cards, boolean[] visited, int number) {
        if (visited[number]) {
            return 0;
        }
        visited[number] = true;
        int nextNumber = boxes[cards.indexOf(number)];
        return dfs(boxes, cards, visited, nextNumber) + 1;
    }
}