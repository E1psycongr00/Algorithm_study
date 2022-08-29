from dataclasses import dataclass
from itertools import chain
from typing import List, Deque
from pprint import pprint

@dataclass(frozen=True)
class Direction:
    dy: int
    dx: int


DOWN = Direction(dy=1, dx=0)
RIGHT = Direction(dy=0, dx=1)
LEFT_UP = Direction(dy=-1, dx=-1)

scheduler = Deque([DOWN, RIGHT, LEFT_UP])


def solution(n: int) -> List[int]:
    '''행렬의 왼쪽 상단 모서리에서 시작하여 오른쪽 하단 모서리에 도달할 때까지 시계 방향 나선으로 이동
    
    Parameters
    ----------
    n : int
        행렬의 크기
    
    Returns
    -------
        정수 목록
    
    '''
    max_step = (n * (n + 1)) // 2
    matrix = [[0] * i for i in range(1, n + 1)]
    y, x = -1, 0
    for i in range(1, max_step + 1):
        next_y, next_x = get_next_position(y, x, scheduler)
        if not is_safe(matrix,n, next_y, next_x):
            scheduler.rotate(-1)
            next_y, next_x = get_next_position(y, x, scheduler)
        y = next_y
        x = next_x

        matrix[y][x] = i
        pprint(matrix)
    return list(chain.from_iterable(matrix))


def get_next_position(y: int, x: int, scheduler: Deque[Direction]) -> tuple[int, int]:
    direction = scheduler[0]
    next_y = y + direction.dy
    next_x = x + direction.dx
    return next_y, next_x


def is_safe(matrix:List[List[int]], n: int, y: int, x: int) -> bool:
    return 0 <= y < n and 0 <= x <= y and matrix[y][x] == 0


print(solution(4))
