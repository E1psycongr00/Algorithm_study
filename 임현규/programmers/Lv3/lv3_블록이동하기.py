from collections import defaultdict, deque
from dataclasses import dataclass


@dataclass(unsafe_hash=True)
class Point:
    y: int
    x: int

    def isInArray(self, row_size, col_size):
        return 0 <= self.y < row_size and 0 <= self.x < col_size

    def getValue(self, board):
        return board[self.y][self.x]

    def __add__(self, other):
        return Point(self.y + other.y, self.x + other.x)


dirs = [Point(-1, 0), Point(1, 0), Point(0, -1), Point(0, 1)]


def solution(board):
    return bfs(board)


def nextPos(p1, p2, m, n, board):
    """
    두 점이 주어졌을 때 두 점의 가능한 모든 다음 위치를 반환
    
    Args:
      p1: Point1
      p2: Point2
      m: 보드의 행 수
      n: 보드의 열 수
      board: 보드
    
    Returns:
      포인트 튜플 목록
    """
    next_pos = []
    # move
    for p in dirs:
        next_p1 = p1 + p
        next_p2 = p2 + p
        if (
            next_p1.isInArray(m, n)
            and next_p2.isInArray(m, n)
            and next_p1.getValue(board) == 0
            and next_p2.getValue(board) == 0
        ):
            next_pos.append((next_p1, next_p2))

    # rotate
    for i in [1, -1]:
        next_p1: Point
        next_p2: Point
        # horizon
        if p1.y == p2.y:
            next_p1 = p1 + Point(i, 0)
            next_p2 = p2 + Point(i, 0)
            if (
                next_p1.isInArray(m, n)
                and next_p2.isInArray(m, n)
                and next_p1.getValue(board) == 0
                and next_p2.getValue(board) == 0
            ):
                next_pos.append((p1, next_p1))
                next_pos.append((p2, next_p2))
        # vertical
        elif p1.x == p2.x:
            next_p1 = p1 + Point(0, i)
            next_p2 = p2 + Point(0, i)
            if (
                next_p1.isInArray(m, n)
                and next_p2.isInArray(m, n)
                and next_p1.getValue(board) == 0
                and next_p2.getValue(board) == 0
            ):
                next_pos.append((p1, next_p1))
                next_pos.append((p2, next_p2))
    return next_pos


def bfs(board):
    m = len(board)
    n = len(board[0])
    q = deque([(Point(0, 0), Point(0, 1))])
    dist = defaultdict(int)
    dist[(Point(0, 0), Point(0, 1))]

    while q:
        p1, p2 = q.popleft()
        if p1 == Point(n - 1, n - 1) or p2 == Point(n - 1, n - 1):
            return dist[(p1, p2)]
        for next in nextPos(p1, p2, m, n, board):
            if next not in dist:
                q.append(next)
                dist[next] = dist[(p1, p2)] + 1
    return None


print(solution([[0, 0, 0, 1, 1],[0, 0, 0, 1, 0],[0, 1, 0, 1, 1],[1, 1, 0, 0, 1],[0, 0, 0, 0, 0]]))

