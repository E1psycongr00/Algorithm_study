import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static int N;
    private static int M;
    private static int originY;
    private static int originX;
    private static int K;
    private static int[][] board;
    private static List<Integer> commands;
    private static int[] dy = new int[] { 0, 0, -1, 1 };
    private static int[] dx = new int[] { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        read();
        Dice dice = new Dice();
        int y = originY;
        int x = originX;
        for (int direction : commands) {
            int ny = y + dy[direction - 1];
            int nx = x + dx[direction - 1];
            if (!isInArray(ny, nx)) {
                continue;
            }
            dice.move(direction);
            y = ny;
            x = nx;
            System.out.println(dice.getTop());
            if (board[y][x] != 0) {
                dice.setBottom(board[y][x]);
                board[y][x] = 0;
            } else {
                board[y][x] = dice.getBottom();
            }

        }
    }

    private static boolean isInArray(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> firstLine = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        N = firstLine.get(0);
        M = firstLine.get(1);
        originY = firstLine.get(2);
        originX = firstLine.get(3);
        K = firstLine.get(4);
        board = new int[N][M];
        for (int i = 0; i < N; ++i) {
            board[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        commands = new ArrayList<>();
        commands.addAll(Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }
}

class Dice {

    private static final int EAST = 1;
    private static final int WEST = 2;
    private static final int NORTH = 3;
    private static final int SOUTH = 4;

    private int[] dice = new int[6];

    public int getTop() {
        return this.dice[0];
    }

    public int getBottom() {
        return this.dice[5];
    }

    public void setBottom(int value) {
        this.dice[5] = value;
    }

    public void move(int direction) {
        if (direction == EAST) {
            moveEast();
        }
        if (direction == WEST) {
            moveWest();
        }
        if (direction == NORTH) {
            moveNorth();
        }
        if (direction == SOUTH) {
            moveSouth();
        }
    }

    public void moveEast() {
        int[] newDice = Arrays.copyOf(this.dice, this.dice.length);
        newDice[0] = this.dice[3];
        newDice[3] = this.dice[5];
        newDice[2] = this.dice[0];
        newDice[5] = this.dice[2];
        this.dice = newDice;
    }

    public void moveWest() {
        int[] newDice = Arrays.copyOf(this.dice, this.dice.length);
        newDice[0] = this.dice[2];
        newDice[3] = this.dice[0];
        newDice[2] = this.dice[5];
        newDice[5] = this.dice[3];
        this.dice = newDice;
    }

    public void moveNorth() {
        int[] newDice = Arrays.copyOf(this.dice, this.dice.length);
        newDice[0] = this.dice[4];
        newDice[1] = this.dice[0];
        newDice[4] = this.dice[5];
        newDice[5] = this.dice[1];
        this.dice = newDice;
    }

    public void moveSouth() {
        int[] newDice = Arrays.copyOf(this.dice, this.dice.length);
        newDice[0] = this.dice[1];
        newDice[1] = this.dice[5];
        newDice[4] = this.dice[0];
        newDice[5] = this.dice[4];
        this.dice = newDice;
    }

    @Override
    public String toString() {
        return "Dice{" +
                "dice=" + Arrays.toString(dice) +
                '}';
    }
}
