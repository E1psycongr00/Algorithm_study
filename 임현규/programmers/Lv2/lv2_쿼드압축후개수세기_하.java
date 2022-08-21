interface CompressorService {

    int[] compress(int[][] arr);
}

class QuadCompressorServiceImpl implements CompressorService {

    private int zeroCnt;
    private int oneCnt;

    @Override
    public int[] compress(int[][] arr) {
        int n = arr.length;
        compressRecursive(arr, 0, 0, n - 1, n - 1);
        
        int[] returnValue = new int[]{zeroCnt, oneCnt};
        reset();
        
        return returnValue;
    }

    private void compressRecursive(int[][] arr, int y1, int x1, int y2, int x2) {
        if (y1 == y2) {
            update(arr[y1][x1]);
            return;
        }
        if (isSameAll(arr, y1, x1, y2, x2)) {
            update(arr[y1][x1]);
            return;
        }
        int midX = (x1 + x2) / 2;
        int midY = (y1 + y2) / 2;

        compressRecursive(arr, y1, x1, midY, midX);
        compressRecursive(arr, y1, midX + 1, midY, x2);
        compressRecursive(arr, midY + 1, x1, y2, midX);
        compressRecursive(arr, midY + 1, midX + 1, y2, x2);

    }

    private boolean isSameAll(int[][] arr, int y1, int x1, int y2, int x2) {
        int val = arr[y1][x1];
        for (int i = y1; i <= y2; ++i) {
            for (int j = x1; j <= x2; ++j) {
                if (arr[i][j] != val) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean update(int val) {
        if (val == 0) {
            zeroCnt++;
            return true;
        }
        if (val == 1) {
            oneCnt++;
            return true;
        }
        return false;
    }
    
    private void reset() {
        zeroCnt = 0;
        oneCnt = 0;
    }
}

class Solution {

    public int[] solution(int[][] arr) {
        CompressorService compressorService = new QuadCompressorServiceImpl();
        return compressorService.compress(arr);
    }
}