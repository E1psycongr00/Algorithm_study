class Solution {

    public int solution(String s) {
        StringSplitter splitter = new StringSplitter();
        return splitter.solve(s);
    }
}

class StringSplitter {

    private static final char INIT = '!';
    private char x = INIT;
    private int xCount = 0;
    private int notXCount = 0;
    private int count = 0;

    public int solve(String s) {
        for (char ch : s.toCharArray()) {
            updateCount(ch);
        }
        if (xCount > 0) {
            count++;
        }
        return count;
    }

    private void updateCount(char ch) {
        if (x == INIT) {
            x = ch;
            xCount++;
            return;
        }
        if (x == ch) {
            xCount++;
            return;
        }
        notXCount++;
        if (xCount == notXCount) {
            count++;
            xCount = 0;
            notXCount = 0;
            x = INIT;
        }
    }

}
