public class Solution {

    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';

    public boolean solution(String s) {
        int count = 0;
        for (char ch : s.toCharArray()) {
            count = countOpenBracket(count, ch);
            count = countCloseBracket(count, ch);
            if (isInvalidCount(count)) {
                break;
            }
        }
        return count == 0;
    }

    private int countOpenBracket(int count, char ch) {
        if (ch == OPEN_BRACKET) {
            return count + 1;
        }
        return count;
    }

    private int countCloseBracket(int count, char ch) {
        if (ch == CLOSE_BRACKET) {
            return count - 1;
        }
        return count;
    }

    private boolean isInvalidCount(int count) {
        return count < 0;
    }
}