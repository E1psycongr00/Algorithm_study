class Solution {
    public int solution(int[][] board, int[][] skill) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] upd = new int[rows+1][cols+1];
        for (int[] query : skill) {
            int type = query[0];
            int r1 = query[1];
            int c1 = query[2];
            int r2 = query[3];
            int c2 = query[4];
            int degree = type == 1 ? -query[5] : query[5];
            
            upd[r1][c1] += degree;
            upd[r2+1][c1] -= degree;
            upd[r1][c2+1] -= degree;
            upd[r2+1][c2+1] += degree;
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                upd[i][j] = upd[i][j-1] + upd[i][j];
            }
        }
        
        for (int j = 0; j < cols; j++) {
            for (int i = 1; i < rows; i++) {
                upd[i][j] = upd[i-1][j] + upd[i][j];
            }
        }
        
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] + upd[i][j] > 0) {
                    count++;
                }
            }
        }
        return count;
    }
}