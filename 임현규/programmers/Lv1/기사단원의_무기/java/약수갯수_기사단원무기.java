import java.util.ArrayList;
import java.util.List;

class Solution {

    public int solution(int number, int limit, int power) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= number; ++i) {
            int divisorCount = Divisor.findDivisorCount(i);
            if (divisorCount > limit) {
                divisorCount = power;
            }
            result.add(divisorCount);
        }
        return result.stream().mapToInt(Integer::intValue).sum();
    }
}

class Divisor {

    public static int findDivisorCount(int n) {
        if (n == 1) {
            return 1;
        }
        int cnt = 0;
        for (int i = 1; i <= (int) Math.sqrt(n); ++i) {
            if (n % i == 0 && n / i == i) {
                cnt++;
            }
            if (n % i == 0 && n / i != i) {
                cnt += 2;
            }
        }
        return cnt;
    }
}