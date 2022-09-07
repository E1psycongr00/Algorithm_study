from functools import reduce
from itertools import permutations
import operator


def solution(expression):
    return max(abs(solve_expression(list(p), expression, 0)) for p in permutations("+-*"))


def solve_expression(priorities, expression, step):
    assert len(priorities) == 3
    if str.isdigit(expression):
        return int(expression)

    op = priorities[step]
    split = expression.split(op)
    tmp = [solve_expression(priorities, exp, step + 1) for exp in split]

    if op == "+":
        return reduce(operator.add, tmp)
    if op == "-":
        return reduce(operator.sub, tmp)
    if op == "*":
        return reduce(operator.mul, tmp)


print(solution("100-200*300-500+20"))
