public class Solution {

    public int solution(int n) {
        int cost = 0;
        while (n > 0) {
            if (isOdd(n)) {
                n -= 1;
                cost += 1;
                continue;
            }
            // 짝수
            n /= 2;
        }
        return cost;
    }

    private boolean isOdd(int number) {
        return number % 2 == 1;
    }
}