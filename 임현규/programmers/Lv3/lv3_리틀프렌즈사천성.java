import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 단순 구현 문제이지만 실수를 많이 해서 굉장히 오래 걸렸다. 하나하나 잘 따져가며 조심스럽게 코드를 짜야한다.
 * 테스트 케이스가 한 개라 예측하는 것도 쉽지 않았고 2017년도 문제는 문제 풀기가 너무 힘들다...
 * 요구사항 그대로 짜주면 된다.
 */
class Solution {

    public String solution(int m, int n, String[] stringBoard) {
        char[][] board = new char[m][n];
        HashMap<Character, List<int[]>> points = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                board[i][j] = stringBoard[i].charAt(j);
                if (Character.isAlphabetic(board[i][j])) {
                    points.computeIfAbsent(board[i][j], k -> new ArrayList<>())
                            .add(new int[] { i, j });
                }
            }
        }
        List<Character> list = new LinkedList<>(points.keySet());
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (!list.isEmpty()) {
            char key = list.get(idx);
            if (canDelete(board, points, key)) {
                delete(board, points, key);
                list.remove(idx);
                sb.append(key);
                idx = 0;
            } else {
                idx++;
                if (idx == list.size()) {
                    return "IMPOSSIBLE";
                }
            }
        }
        return sb.toString();
    }

    private void delete(char[][] board, HashMap<Character, List<int[]>> points, char c) {
        int[] pt1 = points.get(c).get(0);
        int[] pt2 = points.get(c).get(1);
        board[pt1[0]][pt1[1]] = '.';
        board[pt2[0]][pt2[1]] = '.';
    }

    private boolean canDelete(char[][] board, HashMap<Character, List<int[]>> points, char c) {
        int y1 = points.get(c).get(0)[0];
        int x1 = points.get(c).get(0)[1];
        int y2 = points.get(c).get(1)[0];
        int x2 = points.get(c).get(1)[1];

        if (x1 < x2) {
            if (checkRow(board, y1, x1, x2, c) && checkCol(board, x2, y1, y2, c)) {
                return true;
            }
            if (checkRow(board, y2, x1, x2, c) && checkCol(board, x1, y1, y2, c)) {
                return true;
            }
        } else {
            if (checkRow(board, y1, x2, x1, c) && checkCol(board, x2, y1, y2, c)) {
                return true;
            }
            if (checkRow(board, y2, x2, x1, c) && checkCol(board, x1, y1, y2, c)) {
                return true;
            }
        }

        return false;

    }

    private boolean checkRow(char[][] board, int y, int x1, int x2, char c) {
        return IntStream.rangeClosed(x1, x2).allMatch(x -> board[y][x] == '.' || board[y][x] == c);
    }

    private boolean checkCol(char[][] board, int x, int y1, int y2, char c) {
        return IntStream.rangeClosed(y1, y2).allMatch(y -> board[y][x] == '.' || board[y][x] == c);
    }
}