""" 경주로 건설
BFS를 이용해도 되고 다익스트라를 활용해도 된다. 주의점은 방향 상태에 따라 최소값이 달라질 수 있다.
이 의미는 2차원이 아닌 3차원을 최소값 경로에 저장해야 한다.

예를 들어 어떤 두 갈래의 경로가 한 지점에서 만나는데
A 경로 total_cost = 2100
B 경로 total_cost = 2300 이라 가정하자.
만약 A의 경우는 코너를 만들어야 목적지에 도달 가능하고 B의 경우 코너가 없어도 도달 가능한 경우
최종 Cost는 다음과 같다.
A: total_cost = 2700
B: total_cost = 2400

즉 방향 상태가 없는 2차원 좌표만으로는 최소 경로를 보장할 수 없다. 다익스트라의 경우 이미 지나친 경로는
패싱하기 때문에 3차원으로 문제를 풀면 된다.

"""
from collections import defaultdict
from dataclasses import dataclass
from heapq import heappop, heappush


@dataclass
class Info:
    y: int
    x: int
    direction: int
    cost: int

    def __lt__(self, other):
        if self.cost < other.cost:
            return True
        return False


dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

UP = 0
DOWN = 1
LEFT = 2
RIGHT = 3


def solution(board):
    return dij(board) * 100


def dij(board):
    n = len(board)
    heap = []
    if board[0][1] == 0:
        heappush(heap, Info(0, 1, RIGHT, 1))
    if board[1][0] == 0:
        heappush(heap, Info(1, 0, DOWN, 1))
    dist = defaultdict(int)
    for i in range(4):
        dist[(0, 0, i)] = 0
    while heap:
        info = heappop(heap)
        if (info.y, info.x, info.direction) in dist:
            continue
        dist[(info.y, info.x, info.direction)] = info.cost
        # search
        for i in range(4):
            ny = info.y + dy[i]
            nx = info.x + dx[i]
            if not is_safe(ny, nx, n):
                continue
            if (ny, nx, i) in dist or board[ny][nx] == 1:
                continue
            next_cost = info.cost + (1 if info.direction == i else 6)
            heappush(heap, Info(ny, nx, i, next_cost))
    answer = 1_000_000_000        
    for i in range(4):
        if (n-1, n-1, i) in dist:
            answer = min(answer, dist[(n-1, n-1, i)])
    return answer


def is_safe(y, x, n):
    return 0 <= y < n and 0 <= x < n


result = solution([[0, 0, 1, 0], [0, 0, 0, 0], [0, 1, 0, 1], [1, 0, 0, 0]])
print(result)
assert(result == 2100)
