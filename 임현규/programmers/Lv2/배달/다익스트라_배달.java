package backjoon;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {

    public int solution(int N, int[][] road, int K) {
        DijkstraAlgorithm<Integer> dij = initDijkstraAlgorithm(N, road);
        dij.run(1);
        return countLowDistance(dij, N, K);
    }

    private DijkstraAlgorithm<Integer> initDijkstraAlgorithm(int N, int[][] road) {
        DijkstraAlgorithm<Integer> dij = new DijkstraAlgorithm<>();
        for (int i = 1; i <= N; ++i) {
            dij.addVertex(i);
        }
        for (int[] edge : road) {
            dij.addEdge(edge[0], edge[1], edge[2]);
            dij.addEdge(edge[1], edge[0], edge[2]);
        }
        return dij;
    }

    private int countLowDistance(DijkstraAlgorithm<Integer> dij, int N, int K) {
        int count = 0;
        for (int i = 2; i <= N; ++i) {
            if (dij.getDistance(i) <= K) {
                count++;
            }
        }
        return count;
    }
}


class DijkstraAlgorithm<T> {

    private final Map<T, List<Edge<T>>> graph;
    private final Map<T, Integer> distances;
    private final Set<T> visited;
    private final PriorityQueue<Vertex<T>> priorityQueue;

    public DijkstraAlgorithm() {
        this.graph = new HashMap<>();
        this.distances = new HashMap<>();
        this.visited = new HashSet<>();
        this.priorityQueue = new PriorityQueue<>();
    }

    public void addVertex(T vertex) {
        graph.put(vertex, new ArrayList<>());
        distances.put(vertex, Integer.MAX_VALUE);
    }

    public void addEdge(T source, T destination, int weight) {
        graph.get(source).add(new Edge<>(source, destination, weight));
    }

    public void run(T startVertex) {
        distances.put(startVertex, 0);
        priorityQueue.offer(new Vertex<>(startVertex, 0));

        while (!priorityQueue.isEmpty()) {
            T vertex = priorityQueue.poll().vertex;
            if (visited.contains(vertex)) {
                continue;
            }
            visited.add(vertex);

            for (Edge<T> edge : graph.get(vertex)) {
                T dest = edge.destination;
                if (visited.contains(dest)) {
                    continue;
                }
                int newDistance = distances.get(vertex) + edge.weight;
                if (newDistance < distances.get(dest)) {
                    distances.put(dest, newDistance);
                    priorityQueue.offer(new Vertex<>(dest, newDistance));
                }
            }
        }
    }

    public int getDistance(T destination) {
        return distances.get(destination);
    }

    private static class Edge<T> {

        final T source;
        final T destination;
        final int weight;

        Edge(T source, T destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    private static class Vertex<T> implements Comparable<Vertex<T>> {

        final T vertex;
        final int distance;

        Vertex(T vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Vertex<T> other) {
            return Integer.compare(distance, other.distance);
        }
    }
}