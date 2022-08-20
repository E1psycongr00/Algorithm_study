import java.util.HashSet;
import java.util.List;
import java.util.Objects;


interface RobotCommand {

    Point command(char comm, Point p);
}

class Solution {

    public int solution(String dirs) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.play(dirs);
        return gameBoard.getNumberOfWalk();
    }
}

class GameBoard {

    private static final int MAX_X_Y_VALUE = 5;

    private final RobotCommand robotCommand = new DefaultRobotCommand(MAX_X_Y_VALUE);
    private final HashSet<String> visited = new HashSet<>();

    private int numberOfWalk;

    public void play(String commandLine) {
        Point startPoint = new Point(0, 0);
        for (char command : commandLine.toCharArray()) {
            Point destPoint = robotCommand.command(command, startPoint);

            if (!destPoint.equals(startPoint) && !isVisited(startPoint, destPoint)) {
                putKey(startPoint, destPoint);
                numberOfWalk++;
            }
            startPoint = destPoint;
        }
    }

    public int getNumberOfWalk() {
        return this.numberOfWalk;
    }

    private boolean isVisited(Point p1, Point p2) {
        String key1 = p1.getStringPoint() + " " + p2.getStringPoint();
        return visited.contains(key1);
    }

    private void putKey(Point p1, Point p2) {
        String key1 = p1.getStringPoint() + " " + p2.getStringPoint();
        String key2 = p2.getStringPoint() + " " + p1.getStringPoint();
        visited.addAll(List.of(key1, key2));
    }
}

class DefaultRobotCommand implements RobotCommand {

    private final int max_x_y_value;

    public DefaultRobotCommand(int max_x_y_value) {
        this.max_x_y_value = max_x_y_value;
    }


    @Override
    public Point command(char comm, Point p) {
        switch (comm) {
            case 'U':
                return moveUp(p);
            case 'L':
                return moveLeft(p);
            case 'R':
                return moveRight(p);
            case 'D':
                return moveDown(p);
        }
        return null;
    }

    public Point moveUp(Point p) {
        int nextY = p.getY() + 1;
        int nextX = p.getX();
        if (isSafe(nextY, nextX)) {
            return new Point(nextY, nextX);
        }
        return new Point(p);
    }

    public Point moveDown(Point p) {
        int nextY = p.getY() - 1;
        int nextX = p.getX();
        if (isSafe(nextY, nextX)) {
            return new Point(nextY, nextX);
        }
        return new Point(p);
    }

    public Point moveLeft(Point p) {
        int nextY = p.getY();
        int nextX = p.getX() - 1;
        if (isSafe(nextY, nextX)) {
            return new Point(nextY, nextX);
        }
        return new Point(p);
    }

    public Point moveRight(Point p) {
        int nextY = p.getY();
        int nextX = p.getX() + 1;
        if (isSafe(nextY, nextX)) {
            return new Point(nextY, nextX);
        }
        return new Point(p);
    }

    private boolean isSafe(int y, int x) {
        return Math.abs(y) <= max_x_y_value && Math.abs(x) <= max_x_y_value;
    }
}


class Point {

    private final int y;
    private final int x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Point(Point p) {
        this.y = p.getY();
        this.x = p.getX();
    }

    public int getY() {
        return y;
    }


    public int getX() {
        return x;
    }


    public String getStringPoint() {
        return String.format("%d %d", this.y, this.x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return y == point.y && x == point.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }
}