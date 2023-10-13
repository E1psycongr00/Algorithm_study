import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int minValue = Arrays.stream(arr).min().getAsInt();
        int[] result = Arrays.stream(arr).filter(i -> i != minValue).toArray();
        return result.length != 0 ? result : new int[] {-1};
    }
}