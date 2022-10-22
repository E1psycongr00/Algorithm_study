import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> graph = makeListGraph(n, roads);
        int[] dist = bfs(n, graph, destination);
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; ++i) {
            answer[i] = dist[sources[i]];
        }
        return answer;
    }

    private List<List<Integer>> makeListGraph(int n, int[][] roads) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0 ; i < n+1; ++i) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0 ; i < roads.length; ++i) {
            int a = roads[i][0];
            int b = roads[i][1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        return graph;
    }

    private int[] bfs(int n, List<List<Integer>> graph, int src) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(src);
        dist[src] = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            for(int nextNode : graph.get(node)) {
                if (dist[nextNode] > -1) continue;
                dist[nextNode] = dist[node] + 1;
                q.add(nextNode);
            }
        }
        return dist;
    }
}