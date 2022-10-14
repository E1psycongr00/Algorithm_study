class Solution {

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int n = t.length;
        return lowerBound(0, (long) Math.pow(10, 16), x -> {
            long maxGold = 0;
            long maxSilver = 0;
            long total = 0;
            for (int i = 0; i < n; ++i) {
                long cnt = (x - t[i]) / (2L * t[i]) + 1;
                long totalWeightPerOneCity = cnt * w[i];
                maxGold += Math.min(totalWeightPerOneCity, g[i]);
                maxSilver += Math.min(totalWeightPerOneCity, s[i]);
                total += Math.min(totalWeightPerOneCity, g[i] + s[i]);
            }
            return maxGold >= a && maxSilver >= b && total >= a + b;
        });
    }

    private long lowerBound(long lo, long hi, Condition condition) {
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (condition.check(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return hi;

    }

    @FunctionalInterface
    private interface Condition {

        boolean check(long x);
    }
}