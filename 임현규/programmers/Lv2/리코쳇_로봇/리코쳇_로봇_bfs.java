import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {

    private static final int[] dy = {0, 0, 1, -1};
    private static final int[] dx = {1, -1, 0, 0};

    private static final boolean[][] visited = new boolean[100][100];

    public int solution(String[] board) {
        CharBoard charBoard = new CharBoard(board);
        Queue<Node> queue = new ArrayDeque<>();
        Node startNode = findStartNode(charBoard);
        queue.add(startNode);
        assert startNode != null;
        visited[startNode.getY()][startNode.getX()] = true;

        while (!queue.isEmpty()) {
//            System.out.println(queue);
            Node node = queue.poll();
            for (int i = 0; i < 4; ++i) {
                assert node != null;
                Node nextNode = move(charBoard, node, i);
                if (charBoard.get(nextNode.getY(), nextNode.getX()) == 'G') {
                    return node.getCount() + 1;
                }
                if (visited[nextNode.getY()][nextNode.getX()]) {
                    continue;
                }
                queue.add(nextNode);
                visited[nextNode.getY()][nextNode.getX()] = true;
            }
        }
        return -1;
    }

    private Node move(CharBoard charBoard, Node node, int i) {
        int y = node.getY();
        int x = node.getX();
        while (charBoard.isInBoard(y + dy[i], x + dx[i])
            && charBoard.get(y + dy[i], x + dx[i]) != 'D') {
            y += dy[i];
            x += dx[i];
        }
        return new Node(y, x, node.getCount() + 1);
    }

    private Node findStartNode(CharBoard charBoard) {
        for (int i = 0; i < charBoard.getRows(); ++i) {
            for (int j = 0; j < charBoard.getCols(); ++j) {
                if (charBoard.get(i, j) == 'R') {
                    return new Node(i, j);
                }
            }
        }
        return null;
    }


}


class CharBoard {

    private char[][] board;

    public CharBoard(String[] board) {
        initBoard(board);
    }

    public CharBoard(char[][] board) {
        this.board = new char[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i) {
            System.arraycopy(board[i], 0, this.board[i], 0, board[0].length);
        }
    }

    public boolean isInBoard(int y, int x) {
        return y >= 0 && y < board.length && x >= 0 && x < board[0].length;
    }

    public void set(int y, int x, char value) {
        this.board[y][x] = value;
    }

    public char get(int y, int x) {
        return this.board[y][x];
    }

    public int getRows() {
        return board.length;
    }

    public int getCols() {
        return board[0].length;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(board);
    }

    private void initBoard(String[] board) {
        this.board = new char[board.length][board[0].length()];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length(); ++j) {
                this.board[i][j] = board[i].charAt(j);
            }
        }
    }
}

class Node {

    private int y;
    private int x;

    private int count;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Node(int y, int x, int count) {
        this.y = y;
        this.x = x;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Node{" +
            "y=" + y +
            ", x=" + x +
            ", count=" + count +
            '}';
    }
}