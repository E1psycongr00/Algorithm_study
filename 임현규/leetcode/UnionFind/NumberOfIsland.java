
import java.util.stream.IntStream;

/**
 * 격리된 섬 갯수를 세는 솔루션.. 영역을 분리하는 것이므로 UnionFind 로 접근 가능하다.
 * UnionFind는 1차원 배열로 되어있기 때문에 2D-Array 를 1D-Array 로 전환하는 과정이 필요하다.
 * 핵심은
 *  - 2D -> 1D
 *  - left, top 만 탐색해도 됨
 *  - '0'인 배열은 카운트하지 않음.
 *  - merge시 count--
 */
class Solution {

    public int numIslands(char[][] grid) {
        UnionFind uf = new UnionFind(grid);
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1' && i > 0 && grid[i - 1][j] == '1') {
                    uf.merge(i * n + j, (i - 1) * n + j);
                }
                if (grid[i][j] == '1' && j > 0 && grid[i][j - 1] == '1') {
                    uf.merge(i * n + j, i * n + (j - 1));
                }
            }
        }
        return uf.getCount();

    }
}

class UnionFind {

    private int[] parent;
    private int count = 0;

    public UnionFind(char[][] grid) {
        if (grid.length < 1) {
            throw new IllegalStateException("error");
        }
        int m = grid.length;
        int n = grid[0].length;
        count = m * n;
        parent = IntStream.range(0, m * n).toArray();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '0') {
                    count--;
                    parent[i * n + j] = -1;
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
}