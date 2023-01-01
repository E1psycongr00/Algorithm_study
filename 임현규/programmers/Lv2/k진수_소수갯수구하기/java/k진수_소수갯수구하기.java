import java.util.Arrays;

class Solution {

    public int solution(int n, int k) {
        String s = MathHelper.toKth(n, k);
        String[] split = s.split("0");
        return countPrime(split);
    }

    private int countPrime(String[] split) {
        int count = 0;
        for (String s : split) {
            if (!isDigit(s)) {
                continue;
            }
            long parseInt = Long.parseLong(s);
            if (MathHelper.isPrime(parseInt)) {
                count++;
            }
        }
        return count;
    }

    private boolean isDigit(String s) {
        if (s.isBlank()) {
            return false;
        }
        return Arrays.stream(s.chars().toArray()).allMatch(Character::isDigit);
    }
}


class MathHelper {

    public static String toKth(int n, int k) {
        StringBuilder stringBuilder = new StringBuilder();
        int number = n;
        while (number >= k) {
            int mod = number % k;
            stringBuilder.append(mod);
            number /= k;
        }
        stringBuilder.append(number);
        return stringBuilder.reverse().toString();
    }

    public static boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= (long) Math.sqrt(n); ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}