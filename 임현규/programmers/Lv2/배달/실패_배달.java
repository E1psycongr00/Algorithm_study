import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {

    private static final int INF = 1_000_000_000;

    public int solution(int N, int[][] road, int K) {
        Graph graph = new Graph();
        for (int[] edge : road) {
            graph.addEdge(edge);
        }
        return dij(graph, N, 1, K);
    }

    private int dij(Graph graph, int N, int src, int k) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        pq.add(new Edge(src, 0));
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (dist[edge.getTarget()] < edge.getWeight()) {
                continue;
            }

            for (Edge neighbor : graph.getNeighbors(edge.getTarget())) {
                int nextWeight = edge.getWeight() + neighbor.getWeight();
                if (nextWeight < dist[neighbor.getTarget()]) {
                    dist[neighbor.getTarget()] = nextWeight;
                    pq.add(new Edge(neighbor.getTarget(), nextWeight));
                }
            }
        }
        return (int) Arrays.stream(dist)
            .filter(value -> value <= k)
            .count();
    }
}

class Graph {

    private final Map<Integer, List<Edge>> graph;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void addEdge(int[] edge) {
        int a = edge[0];
        int b = edge[1];
        int weight = edge[2];
        this.graph.computeIfAbsent(a, k -> new ArrayList<>()).add(new Edge(b, weight));
        this.graph.computeIfAbsent(b, k -> new ArrayList<>()).add(new Edge(a, weight));
    }

    public List<Edge> getNeighbors(int src) {
        return graph.get(src);
    }

    @Override
    public String toString() {
        return "Graph{" +
            "graph=" + graph +
            '}';
    }
}

class Edge implements Comparable<Edge> {

    int target;
    int weight;

    public Edge(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    public int getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
            "target=" + target +
            ", weight=" + weight +
            '}';
    }
}