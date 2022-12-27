package backjoon;


import java.util.Arrays;

class Solution {

    private int countResult = Integer.MAX_VALUE;

    public int solution(int[][] beginning, int[][] target) {
        Board beginBoard = new Board(beginning);
        Board targetBoard = new Board(target);
        backTrack(beginBoard, targetBoard, 0, 0);
        return countResult == Integer.MAX_VALUE ? -1 : countResult;
    }

    private void backTrack(Board beginBoard, Board targetBoard, int count, int r) {
        if (r == beginBoard.getRowSize()) {
            for (int col = 0; col < beginBoard.getColSize(); ++col) {
                if (beginBoard.allMatchCol(targetBoard, col)) {
                    continue;
                }
                if (beginBoard.allDisMatchCol(targetBoard, col)) {
                    count++;
                    continue;
                }
                return;
            }
            countResult = Integer.min(countResult, count);
            return;
        }
        for (int row = 0; row < beginBoard.getRowSize(); ++row) {
            backTrack(beginBoard, targetBoard, count, r + 1);
            beginBoard.flipRow(row);
            backTrack(beginBoard, targetBoard, count + 1, r + 1);
            beginBoard.flipRow(row);
        }
    }
}

class Board {

    private final int[][] board;
    private final int rowSize;
    private final int colSize;

    public Board(int[][] board) {
        this.board = board;
        this.rowSize = board.length;
        this.colSize = board[0].length;
    }

    public void flipRow(int row) {
        for (int i = 0; i < colSize; ++i) {
            this.board[row][i] ^= 1;
        }
    }

    public boolean allMatchCol(Board other, int col) {
        for (int i = 0; i < rowSize; ++i) {
            if (this.board[i][col] != other.board[i][col]) {
                return false;
            }
        }
        return true;
    }

    public boolean allDisMatchCol(Board other, int col) {
        for (int i = 0; i < rowSize; ++i) {
            if (this.board[i][col] == other.board[i][col]) {
                return false;
            }
        }
        return true;
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < rowSize; ++i) {
            stringBuilder.append(Arrays.toString(this.board[i])).append("\n");
        }
        return stringBuilder.toString();
    }
}