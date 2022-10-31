import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

enum Direction {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1);

    private final int dy;
    private final int dx;

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

public class Main {

    private static final Direction[] directions = new Direction[] { Direction.UP, Direction.RIGHT,
            Direction.DOWN, Direction.LEFT };
    private static int n;
    private static int m;
    private static int r;
    private static int c;
    private static int d;

    private static boolean keepGo = true;
    private static int[][] board = new int[50][50];
    private static int cnt;

    public static void main(String[] args) throws IOException {
        read();
        while (keepGo) {
            command();
            // print();
        }
        System.out.println(cnt);

    }

    private static void command() {
        if (board[r][c] == 0) {
            board[r][c] = -1;
            cnt++;
        }

        for (int i = 1; i <= 4; ++i) {
            int dd = (d - i + 4) % 4;
            int ny = r + directions[dd].getDy();
            int nx = c + directions[dd].getDx();
            if (!isInArray(ny, nx)) {
                continue;
            }
            if (board[ny][nx] == 1 || board[ny][nx] == -1) {
                continue;
            }
            r = ny;
            c = nx;
            d = dd;
            return;
        }
        int dd = (d + 2) % 4;
        int backY = r + directions[dd].getDy();
        int backX = c + directions[dd].getDx();
        if (!isInArray(backY, backX)) {
            keepGo = false;
        }
        if (board[backY][backX] == 1) {
            keepGo = false;
        }
        r = backY;
        c = backX;
    }

    private static boolean isInArray(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        n = Integer.parseInt(firstLine[0]);
        m = Integer.parseInt(firstLine[1]);
        String[] secondLine = br.readLine().split(" ");
        r = Integer.parseInt(secondLine[0]);
        c = Integer.parseInt(secondLine[1]);
        d = Integer.parseInt(secondLine[2]);
        for (int i = 0; i < n; ++i) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    private static void print() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                System.out.printf("%d ", board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
