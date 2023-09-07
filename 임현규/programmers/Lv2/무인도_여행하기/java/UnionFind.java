import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {

    public int[] solution(String[] maps) {
        UnionFind uf = merge(maps);
        Map<Integer, Integer> islands = new HashMap<>();
        for (int i = 0; i < maps.length; ++i) {
            for (int j = 0; j < maps[0].length(); ++j) {
                if (maps[i].charAt(j) == 'X') {
                    continue;
                }
                int node = i * maps[0].length() + j;
                int root = uf.find(node);
                int value = maps[i].charAt(j) - '0';
                islands.put(root, islands.getOrDefault(root, 0) + value);
            }
        }
        int[] result =  islands.values().stream().mapToInt(Integer::intValue).sorted().toArray();
        return result.length == 0 ? new int[]{-1} : result;
    }

    private UnionFind merge(String[] maps) {
        int rows = maps.length;
        int cols = maps[0].length();
        UnionFind uf = new UnionFind(rows * cols);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (maps[i].charAt(j) == 'X') {
                    continue;
                }
                int node = i * cols + j;
                int rightNode = node + 1;
                int downNode = node + cols;
                if (j + 1 < cols && maps[i].charAt(j + 1) != 'X') {
                    uf.merge(node, rightNode);
                }
                if (i + 1 < rows && maps[i + 1].charAt(j) != 'X') {
                    uf.merge(node, downNode);
                }
            }
        }
        return uf;
    }

    static class UnionFind {

        private final int[] parent;

        public UnionFind(int size) {
            this.parent = IntStream.range(0, size).toArray();
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void merge(int x, int y) {
            x = find(x);
            y = find(y);
            if (x != y) {
                parent[y] = x;
            }
        }
    }
}