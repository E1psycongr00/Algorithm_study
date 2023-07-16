import java.util.HashSet;
import java.util.Set;

class Solution {

    private static final int[] dy = {1, -1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};

    public int solution(String dirs) {
        int y = 0;
        int x = 0;
        Set<Edge> edges = new HashSet<>();
        for (char dir : dirs.toCharArray()) {
            int d = mapper(dir);
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny < -5 || ny > 5 || nx < -5 || nx > 5) {
                continue;
            }
            edges.add(new Edge(y, x, ny, nx));
            y = ny;
            x = nx;
        }
        return edges.size();
    }

    private int mapper(char dir) {
        return switch (dir) {
            case 'D' -> 0;
            case 'U' -> 1;
            case 'R' -> 2;
            case 'L' -> 3;
            default -> throw new IllegalArgumentException();
        };
    }

    private record Edge(int sourceX, int sourceY, int targetX, int targetY) {

        Edge {
            if (targetX < sourceX) {
                int temp = targetX;
                targetX = sourceX;
                sourceX = temp;
            }
            if (targetY < sourceY) {
                int temp = targetY;
                targetY = sourceY;
                sourceY = temp;
            }
        }
    }
}