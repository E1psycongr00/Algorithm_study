import java.util.ArrayList;
import java.util.List;

class Solution {

    private static final char SEA = 'X';

    public int[] solution(String[] maps) {
        int rowSize = maps.length;
        int colSize = maps[0].length();
        List<Integer> dayOfIslands = new ArrayList<>();
        boolean[][] visited = new boolean[rowSize + 1][colSize + 1];
        for (int i = 0; i < rowSize; ++i) {
            for (int j = 0; j < colSize; ++j) {
                updateDaysOfIslands(maps, dayOfIslands, visited, i, j);
            }
        }
        if (dayOfIslands.isEmpty()) {
            return new int[] {-1};
        }
        return dayOfIslands.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    private void updateDaysOfIslands(String[] maps, List<Integer> dayOfIslands, boolean[][] visited,
        int y,
        int x) {
        int count = 0;
        if (!visited[y][x] && !isSea(maps, y, x)) {
            count += sumDaysByIsland(maps, visited, y, x);
        }
        if (count > 0) {
            dayOfIslands.add(count);
        }
    }

    private int sumDaysByIsland(String[] maps, boolean[][] visited, int y, int x) {
        visited[y][x] = true;
        int sum = maps[y].charAt(x) - '0';
        for (Direction direction : Direction.values()) {
            int currentY = y + direction.getDy();
            int currentX = x + direction.getDx();
            if (!isInBound(maps, currentY, currentX)) {
                continue;
            }
            if (isSea(maps, currentY, currentX) || visited[currentY][currentX]) {
                continue;
            }
            sum += sumDaysByIsland(maps, visited, currentY, currentX);
        }
        return sum;
    }

    private boolean isInBound(String[] maps, int currentY, int currentX) {
        int rowSize = maps.length;
        int colSize = maps[0].length();
        return 0 <= currentY && currentY < rowSize && 0 <= currentX && currentX < colSize;
    }

    private boolean isSea(String[] maps, int currentY, int currentX) {
        return maps[currentY].charAt(currentX) == SEA;
    }


    public enum Direction {
        LEFT(0, -1),
        RIGHT(0, 1),
        UP(-1, 0),
        DOWN(1, 0);
        private int dy;
        private int dx;

        Direction(int dy, int dx) {
            this.dy = dy;
            this.dx = dx;
        }

        public int getDy() {
            return dy;
        }

        public int getDx() {
            return dx;
        }
    }
}