import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * dp 식은 다음과 같다.
 * dp[time][node] = min(dp[time][node], dp[time-1][adjNode]) + (gps_log[time] ==
 * node) ? 0 : 1
 * GPS 문제는 최소한의 gps_log를 경로에 맞게 수정해야 한다.
 * 위 예제의 경우 gps_log = [1,2,3,3,6,7] 이 나오는데 6을 5로 한번 수정하면 가능하다고 한다.
 * 이를 다르게 해석하면 (time, 5) = 1 이 됨을 의미하고 최종 7에서는 인접된 5, 6 노드를 가저오면 된다.
 * 6도 인접된 4도 해당 로그에선 지나지 않는 노드임으로 1이 되어야 한다. 즉 +1의 조건은
 * gps_log[time] == node ? 일 때가 된다.
 * 이를 DP로 접근하는것도 쉽지 않지만 gps_log[time] == node ? 0 : 1 을 접근하는 것이 더욱더 쉽지 않은 문제 같다.
 */
class Solution {

    private static final int INF = (int) 1e9;

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        List<List<Integer>> graph = makeLinkedGraph(n, edge_list, gps_log);

        int[][] dp = new int[n + 1][n + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, INF);
        }

        dp[0][gps_log[0]] = 0;
        
        for (int time = 1; time < k; ++time) {
            for (int node = 1; node <= n; ++node) {
                for (int adjNode : graph.get(node)) {
                    dp[time][node] = Math.min(dp[time - 1][adjNode], dp[time][node]);
                }
                dp[time][node] += (gps_log[time] == node) ? 0 : 1;
            }
        }
        int answer = dp[k - 1][gps_log[k - 1]];
        return (answer < INF) ? answer : -1;
    }

    private List<List<Integer>> makeLinkedGraph(int n, int[][] edge_list, int[] gps_log) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; ++i) {
            graph.add(new ArrayList<>());
        }
        for (int[] ints : edge_list) {
            int a = ints[0];
            int b = ints[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
            // 정차한 경우 떄문에 본인 노드도 인접노드에 넣어준다.
            graph.get(a).add(a);
            graph.get(b).add(b);
        }
        return graph;
    }
}