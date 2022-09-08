
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.IntStream;

class Solution {

    public int[] solution(int m, int n, int[][] picture) {
        UnionFind uf = new UnionFind(m, n, picture);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] > 0) {
                    if (i > 0 && picture[i][j] == picture[i - 1][j]) {
                        uf.merge(i * n + j, (i - 1) * n + j);
                    }
                    if (j > 0 && picture[i][j] == picture[i][j - 1]) {
                        uf.merge(i * n + j, i * n + (j - 1));
                    }
                }
            }
        }
        int cnt = uf.getCount();
        int maxArea = uf.getMaxArea();
        return new int[] { cnt, maxArea };
    }
}

class UnionFind {

    private final int[] parent;
    private final int dummy;

    private int count;

    public UnionFind(int m, int n, int[][] picture) {
        parent = IntStream.range(0, m * n + 1).toArray();
        dummy = m * n;
        count = m * n;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 0) {
                    count--;
                    parent[i * n + j] = dummy;
                }
            }
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void merge(int n1, int n2) {
        n1 = find(n1);
        n2 = find(n2);
        if (n1 != n2) {
            parent[n2] = n1;
            count--;
        }
    }

    public int getCount() {
        return count;
    }

    public int getMaxArea() {
        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i <= dummy; ++i) {
            int key = find(i);
            if (parent[key] != dummy) {
                counter.put(key, counter.getOrDefault(key, 0) + 1);
            }

        }
        return counter.entrySet().stream().max(Comparator.comparing(Entry::getValue))
                .map(Entry::getValue).orElse(-1);
    }

    @Override
    public String toString() {
        return "UnionFind{" +
                "parent=" + Arrays.toString(parent) +
                ", count=" + count +
                '}';
    }
}