import java.util.Stack;

class Solution {
    public int[] solution(int[] numbers) {
        return findBackBigNumbers(numbers);
    }

    private int[] findBackBigNumbers(int[] numbers) {
        int[] result = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < numbers.length; ++i) {
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                int index = stack.pop();
                result[index] = numbers[i];
            }
            stack.add(i);
        }
        
        while (!stack.isEmpty()) {
            int index = stack.pop();
            result[index] = -1;
        }
        return result;
    }
}

