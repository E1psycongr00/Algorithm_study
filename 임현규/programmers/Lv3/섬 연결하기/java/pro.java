import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int[][] costs) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::getCost));
        for (int i = 0; i < costs.length; i++) {
            pq.add(new Edge(costs[i][0], costs[i][1], costs[i][2]));
        }
        UnionFind uf=  new UnionFind(n);
        int totalCost = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (!uf.isConnected(edge.getNode1(), edge.getNode2())) {
                uf.merge(edge.getNode1(), edge.getNode2());
                totalCost += edge.getCost();
            }
        }
        return totalCost;
    }
    
    private static class Edge {
        private final int node1;
        private final int node2;
        private final int cost;
        
        public Edge(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        public int getNode1() {
            return this.node1;
        }
        
        public int getNode2() {
            return this.node2;
        }
        
        public int getCost() {
            return this.cost;
        }
        
        @Override
        public String toString() {
            return String.format("edge{%d, %d, %d}", node1, node2, cost);
        }
    }
    
    private static class UnionFind {
        private final int[] parents;
        
        public UnionFind(int size) {
            this.parents = IntStream.range(0, size).toArray();
        }
        
        public int find(int x) {
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }
        
        public void merge(int x, int y) {
            x = find(x);
            y = find(y);
            if (x != y) {
                parents[y] = x;
            }
        }
        
        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }
}