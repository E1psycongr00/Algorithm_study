import java.util.*;

class Solution {
    public long solution(int[] arr) {
        long[] p = new long[arr.length + 1];
        p[1] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            p[i + 1] = p[i] + (long)Math.pow(-1, i) * arr[i]; 
        }
        long maxValue = Arrays.stream(p).max().orElseThrow();
        long minValue = Arrays.stream(p).min().orElseThrow();
        return maxValue - minValue;
    }
}