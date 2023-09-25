import java.util.*;
import java.util.stream.*;

class Solution {

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < wires.length; i++) {
            UnionFind uf = new UnionFind(n);
            for (int j = 0; j < wires.length; j++) {
                if (i == j) {
                    continue;
                }
                uf.merge(wires[j][0], wires[j][1]);
            }
            answer = Math.min(answer, uf.getDifference());
        }
        return answer;
    }

    private static class UnionFind {

        private final int[] parents;

        public UnionFind(int size) {
            this.parents = IntStream.range(0, size + 1).toArray();
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

        public int getDifference() {
            Map<Integer, Integer> frequency = new HashMap<>();
            for (int i = 1; i < parents.length; i++) {
                int node = find(i);
                frequency.compute(node, (k, v) -> v == null ? 1 : v + 1);
            }
            List<Integer> values = frequency.values().stream().collect(Collectors.toList());
            return Math.abs(values.get(0) - values.get(1));
        }
    }
}