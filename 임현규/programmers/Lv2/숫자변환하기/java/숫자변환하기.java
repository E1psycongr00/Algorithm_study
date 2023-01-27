import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

    private static final int MAX_VALUE = 1_000_000;
    private final int[] visitCount = new int[MAX_VALUE + 1];
    private final Queue<Integer> queue = new ArrayDeque<>();

    public int solution(int x, int y, int n) {
        return bfs(x, y, n);
    }

    private int bfs(int source, int target, int n) {
        queue.add(source);
        while (!queue.isEmpty()) {
            int value = queue.poll();
            if (value == target) {
                return visitCount[value];
            }
            addQueue(target, value, value + n);
            addQueue(target, value, value * 2);
            addQueue(target, value, value * 3);
        }
        return -1;
    }

    private void addQueue(int target, int value, int nextValue) {
        if (nextValue <= target && visitCount[nextValue] == 0) {
            queue.add(nextValue);
            visitCount[nextValue] = visitCount[value] + 1;
        }
    }
}