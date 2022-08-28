from dataclasses import dataclass
from pprint import pprint

@dataclass(frozen=True)
class Point:
    x: int
    y: int


def solve(eq1, eq2):
    a, b, e = eq1
    c, d, f = eq2
    div = a * d - b * c
    if div == 0:
        return None
    x = (b * f - e * d) / div
    y = (e * c - a * f) / div
    int_x = int(x)
    int_y = int(y)
    if x != int_x or y != int_y:
        return None
    return Point(x=int_x, y=int_y)


def draw(solution_points):
    right_x_size = max(solution_points, key=lambda p: p.x).x
    left_x_size= min(solution_points, key=lambda p: p.x).x
    top_y_size = max(solution_points, key=lambda p: p.y).y
    bottom_y_size = min(solution_points, key=lambda p: p.y).y
    
    zero_x = - left_x_size
    zero_y = top_y_size
    board = [["."] * (right_x_size - left_x_size + 1) for _ in range(top_y_size - bottom_y_size + 1)]
    for p in solution_points:
        row = zero_y - p.y
        col = zero_x + p.x
        board[row][col] = '*'
    result = []
    for b in board:
        result.append("".join(b))
    return result


def solution(line):
    solution_points = set()
    for i in range(len(line)):
        for j in range(len(line)):
            if i != j:
                solution_points.add(solve(line[i], line[j]))
    if None in solution_points:
        solution_points.remove(None)
    return draw(solution_points)


pprint(solution([[1, -1, 0], [2, -1, 0]]))


