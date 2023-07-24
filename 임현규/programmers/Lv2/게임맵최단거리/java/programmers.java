import java.util.*;

class Solution {
    private static final int[] dy = new int[] {0, -1, 0, 1};
    private static final int[] dx = new int[] {-1, 0, 1, 0};
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        int[][] dist = new int[n][m];
        
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0));
        dist[0][0] = 1;
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            if (p.y == n -1 && p.x == m - 1) {
                return dist[p.y][p.x];
            }
            for (int i = 0; i < 4; ++i) {
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                    continue;
                }
                if (dist[ny][nx] == 0 && maps[ny][nx] == 1) {
                    q.add(new Point(ny, nx));
                    dist[ny][nx] = dist[p.y][p.x] + 1;
                }
            }
        }
        
        return -1;
    }
    
    private static class Point {
        private final int y;
        private final int x;
        
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}