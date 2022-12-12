import java.util.Stack;

public class Solution {

    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';

    public boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        try {
            process(stack, s);
            validateStack(stack);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private void process(Stack<Character> stack, String s) {
        for (char ch : s.toCharArray()) {
            if (ch == OPEN_BRACKET) {
                stack.push(ch);
                continue;
            }
            if (ch == CLOSE_BRACKET) {
                stack.pop();
            }
        }
    }

    private void validateStack(Stack<Character> stack) {
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}