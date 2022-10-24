import java.util.HashMap;
import java.util.Map;


class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> leftCount = new HashMap<>();
        Map<Integer, Integer> rightCount = new HashMap<>();
        for (int top : topping) {
            rightCount.put(top, rightCount.getOrDefault(top, 0) + 1);
        }
        int cnt = 0;
        for (int top : topping) {
            leftCount.put(top, rightCount.getOrDefault(top, 0) + 1);
            rightCount.put(top, rightCount.get(top) - 1);
            if (rightCount.get(top) == 0) {
                rightCount.remove(top);
            }
            if (leftCount.size() == rightCount.size()) {
                cnt += 1;
            }
        }
        return cnt;
    }
}