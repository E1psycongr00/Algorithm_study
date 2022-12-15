import java.util.PriorityQueue;

class Solution {

    public int solution(int n, int k, int[] enemy) {
        final RoundCalculator roundCalculator = new RoundCalculator(n, k);
        for (int count = 0; count < enemy.length; ++count) {
            if (roundCalculator.push(enemy[count])) {
                continue;
            }
            return count;
        }
        return enemy.length;
    }
}

class RoundCalculator {

    private final PriorityQueue<Integer> pq = new PriorityQueue<>();
    private final int limitSum;
    private final int size;
    private int sum = 0;

    public RoundCalculator(int limitSum, int size) {
        this.size = size;
        this.limitSum = limitSum;
    }

    public boolean push(int number) {
        if (pq.size() < size) {
            pq.add(number);
            return true;
        }
        pq.add(number);
        sum += pq.poll();
        return sum <= limitSum;
    }
}