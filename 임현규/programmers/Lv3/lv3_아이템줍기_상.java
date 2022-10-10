import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


class Solution {

    private static final int LINE = 1;
    private static final int INVALID = 0;

    private final int[] dy = new int[]{0, 0, -1, 1};
    private final int[] dx = new int[]{-1, 1, 0, 0};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[102][102];
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;
        List<int[]> sizeUpRectangle = new ArrayList<>();
        drawLineAndSizeUpRectangle(sizeUpRectangle, rectangle, map);
        int answer = bfs(sizeUpRectangle, map, characterX, characterY, itemX, itemY);
        return answer / 2;
    }

    private void drawLineAndSizeUpRectangle(List<int[]> sizeUpRectangle, int[][] rectangle,
        int[][] map) {
        for (int[] rect : rectangle) {
            int minX = rect[0] * 2;
            int minY = rect[1] * 2;
            int maxX = rect[2] * 2;
            int maxY = rect[3] * 2;
            sizeUpRectangle.add(new int[]{minX, minY, maxX, maxY});
            for (int x = minX; x <= maxX; ++x) {
                for (int y = minY; y <= maxY; ++y) {
                    map[x][y] = LINE;
                }
            }
        }
    }

    private int bfs(List<int[]> sizeUpRectangle, int[][] map, int srcX, int srcY, int targetX,
        int targetY) {
        Deque<Info> q = new ArrayDeque<>();
        q.add(new Info(0, srcX, srcY));
        map[srcX][srcY] = INVALID;
        while (!q.isEmpty()) {
            Info info = q.pollFirst();
            if (info.x == targetX && info.y == targetY) {
                return info.dist;
            }
            for (int i = 0; i < 4; ++i) {
                int ny = info.y + dy[i];
                int nx = info.x + dx[i];
                if (map[nx][ny] == LINE && isOutPointFromRectangle(nx, ny, sizeUpRectangle)) {
                    map[nx][ny] = INVALID;
                    q.add(new Info(info.dist + 1, nx, ny));
                }
            }
        }
        return -1;
    }

    private boolean isOutPointFromRectangle(int nx, int ny, List<int[]> sizeUpRectangle) {
        for (int[] rect : sizeUpRectangle) {
            int minX = rect[0];
            int minY = rect[1];
            int maxX = rect[2];
            int maxY = rect[3];
            if (minX < nx && nx < maxX && minY < ny && ny < maxY) {
                return false;
            }
        }
        return true;
    }

    static class Info {

        private final int dist;
        private final int x;
        private final int y;

        public Info(int dist, int x, int y) {
            this.dist = dist;
            this.x = x;
            this.y = y;
        }
    }
}