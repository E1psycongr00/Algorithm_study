package backjoon;


import backjoon.BFS.Node;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

class Solution {

    public int solution(String[] maps) {
        BFS bfs = new BFS(maps);
        Node leverPosition = bfs.getPosition('L');
        Node startPosition = bfs.getPosition('S');
        int dist1 = bfs.search(startPosition.getY(), startPosition.getX(), 'L');
        int dist2 = bfs.search(leverPosition.getY(), leverPosition.getX(), 'E');
        if (dist1 == -1 || dist2 == -1) {
            return -1;
        }
        return dist1 + dist2;
    }
}

class BFS {

    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};
    private final Board board;
    private final int rows;
    private final int cols;

    public BFS(String[] board) {
        this.board = new Board(board);
        this.rows = board.length;
        this.cols = board[0].length();
    }

    public Node getPosition(char target) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board.isSame(row, col, target)) {
                    return new Node(row, col, 0);
                }
            }
        }
        return null;
    }

    public int search(int startY, int startX, char target) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows][cols];
        queue.add(new Node(startY, startX, 0));
        visited[startY][startX] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (board.isSame(node.y, node.x, target)) {
                return node.distance;
            }
            for (int i = 0; i < 4; i++) {
                int nextY = node.y + dy[i];
                int nextX = node.x + dx[i];
                if (nextY < 0 || nextY >= rows || nextX < 0 || nextX >= cols) {
                    continue;
                }
                if (visited[nextY][nextX]) {
                    continue;
                }
                if (board.isSame(nextY, nextX, Board.WALL)) {
                    continue;
                }
                queue.add(new Node(nextY, nextX, node.distance + 1));
                visited[nextY][nextX] = true;
            }
        }
        return -1;
    }

    public static class Node {

        private final int y;
        private final int x;
        private final int distance;

        public Node(int y, int x, int distance) {
            this.y = y;
            this.x = x;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return y == node.y && x == node.x && distance == node.distance;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x, distance);
        }

        @Override
        public String toString() {
            return "Node{" +
                "y=" + y +
                ", x=" + x +
                ", distance=" + distance +
                '}';
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public int getDistance() {
            return distance;
        }
    }

    private static class Board {

        private static final char START = 'S';
        private static final char END = 'E';
        private static final char WALL = 'X';
        private static final char EMPTY = 'O';
        private static final char LEVER = 'L';

        private final char[][] board;

        public Board(String[] board) {
            final int rows = board.length;
            final int cols = board[0].length();
            this.board = new char[rows][cols];
            for (int i = 0; i < board.length; i++) {
                this.board[i] = board[i].toCharArray();
            }
        }

        public char get(int row, int col) {
            return board[row][col];
        }

        public boolean isSame(int row, int col, char target) {
            return board[row][col] == target;
        }
    }
}