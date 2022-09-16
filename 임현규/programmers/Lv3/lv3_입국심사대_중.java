import java.util.Arrays;

class Solution {

    public long solution(int n, int[] times) {
        long lo = 1;
        long hi = (long)1e18;
        return binarySearch(lo, hi, n, times);
    }

    private long binarySearch(long lo, long hi, int n, int[] times) {

        while (lo < hi) {
            long mid = (lo + hi) / 2;
            if (condition(mid, n, times)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return hi;
    }

    private boolean condition(long mid, int n, int[] times) {
        return Arrays.stream(times)
            .mapToLong(time -> mid / time)
            .sum() >= n;
    }
}
