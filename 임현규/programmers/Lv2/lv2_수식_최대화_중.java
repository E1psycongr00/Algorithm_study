import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Solution {

    public long solution(String expression) {
        List<String[]> priorities = new ArrayList<>();
        genCaseOfPriorities(new String[3], 0, new HashSet<>(), priorities);

        Calculator calculator = new Calculator(expression);
        long answer = priorities.stream().map(calculator::calculate)
                .map(Math::abs).mapToLong(value -> value).max().getAsLong();
        return answer;
    }

    private void genCaseOfPriorities(String[] picked, int step, HashSet<String> visited,
            List<String[]> answer) {
        if (step == 3) {
            answer.add(picked.clone());
            return;
        }
        String[] ops = new String[] { "\\+", "\\-", "\\*" };
        for (String op : ops) {
            if (visited.contains(op)) {
                continue;
            }
            picked[step] = op;
            visited.add(op);
            genCaseOfPriorities(picked, step + 1, visited, answer);
            picked[step] = null;
            visited.remove(op);
        }
    }
}

class Calculator {

    private final String expression;

    public Calculator(String expression) {
        this.expression = expression;
    }

    public long calculate(String[] priorities) {
        return calculate(priorities, this.expression, 0);
    }

    private long calculate(String[] priorities, String expression, int step) {
        if (isDigit(expression)) {
            return Long.parseLong(expression);
        }
        ArrayList<Long> tmp = new ArrayList<>();
        String[] split = expression.split(priorities[step]);
        for (String exp : split) {
            tmp.add(calculate(priorities, exp, step + 1));
        }
        return operate(priorities[step], tmp);
    }

    private long operate(String op, List<Long> tmp) {
        if (op.equals("\\+")) {
            return tmp.stream().reduce((i1, i2) -> i1 + i2).get();
        }
        if (op.equals("\\-")) {
            return tmp.stream().reduce((i1, i2) -> i1 - i2).get();
        }
        if (op.equals("\\*")) {
            return tmp.stream().reduce((i1, i2) -> i1 * i2).get();
        }
        throw new RuntimeException("맞지 않은 형식");
    }

    private boolean isDigit(String s) {
        return s.chars().allMatch(Character::isDigit);
    }
}