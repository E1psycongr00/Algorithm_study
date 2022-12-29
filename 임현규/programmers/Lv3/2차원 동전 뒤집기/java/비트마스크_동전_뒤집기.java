import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {

    private static final int INF = Integer.MAX_VALUE;

    public int solution(int[][] beginning, int[][] target) {
        Board beginBoard = new Board(beginning);
        Board targetBoard = new Board(target);
        List<List<Integer>> subsets = makeSubSet(beginning);
        int count = INF;
        for (List<Integer> subset : subsets) {
            count = Integer.min(count, countBoard(beginBoard, targetBoard, subset));
        }
        return count == INF ? -1 : count;
    }

    private int countBoard(Board source, Board target, List<Integer> subset) {
        int count = subset.size();
        Board newBoard = flipBoard(source, subset);
        for (int col = 0; col < newBoard.getColSize(); ++col) {
            if (newBoard.checkAllRowsColumnMatch(target, col)) {
                continue;
            }
            if (newBoard.checkAllRowsColumnDisMatch(target, col)) {
                count++;
                continue;
            }
            return INF;
        }
        return count;
    }

    private Board flipBoard(Board board, List<Integer> subset) {
        Board newBoard = new Board(board);
        for (int sub : subset) {
            newBoard.flipRow(sub);
        }
        return newBoard;
    }

    private List<List<Integer>> makeSubSet(int[][] board) {
        return SubSetGenerator.subsets(
            IntStream.range(0, board.length)
                .boxed()
                .collect(Collectors.toList()));
    }
}

class SubSetGenerator {

    public static <T extends Comparable<? super T>> List<List<T>> subsets(List<T> items) {
        List<List<T>> list = new ArrayList<>();
        Collections.sort(items);
        backTrack(list, new ArrayList<>(), items, 0);
        return list;
    }

    private static <T> void backTrack(List<List<T>> list, List<T> tempList, List<T> items,
        int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < items.size(); ++i) {
            tempList.add(items.get(i));
            backTrack(list, tempList, items, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}

class Board {

    private final int[] elements;
    private final int allOne;
    private final int rowSize;
    private final int colSize;

    public Board(int[][] board) {
        this.elements = init(board);
        this.allOne = (1 << board[0].length) - 1;
        this.rowSize = board.length;
        this.colSize = board[0].length;
    }

    public Board(Board board) {
        this.elements = Arrays.copyOf(board.elements, board.rowSize);
        this.allOne = board.allOne;
        this.rowSize = board.rowSize;
        this.colSize = board.colSize;
    }

    public void flipRow(int row) {
        elements[row] ^= allOne;
    }

    public boolean checkAllRowsColumnMatch(Board other, int col) {
        for (int i = 0; i < rowSize; ++i) {
            if (((elements[i] & (1 << col)) != (other.elements[i] & (1 << col)))) {
                return false;
            }
        }
        return true;
    }

    public boolean checkAllRowsColumnDisMatch(Board other, int col) {
        for (int i = 0; i < rowSize; ++i) {
            if (((elements[i] & (1 << col)) == (other.elements[i] & (1 << col)))) {
                return false;
            }
        }
        return true;
    }

    private int[] init(int[][] board) {
        int[] elements = new int[board.length];
        for (int i = 0; i < board.length; ++i) {
            int temp = 0;
            for (int j = 0; j < board[0].length; ++j) {
                temp |= (board[i][j] << j);
            }
            elements[i] = temp;
        }
        return elements;
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }

    @Override
    public String toString() {
        return "Board{" +
            "elements=" + Arrays.toString(elements) +
            ", allOne=" + allOne +
            '}';
    }
}

