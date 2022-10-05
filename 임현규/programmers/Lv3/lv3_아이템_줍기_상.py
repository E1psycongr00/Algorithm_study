"""  
    그래프 구현 문제로 구현의 핵심은 2가지이다.
    1. 모든 좌표 2배로 늘리기
    2. 이동 경로 구현하기
    3. 외곽선을 인식해 최단경로 구하기

    이동 경로는 오로지 직사각형 둘레만을 건너야 한다. 사실 간단하다.
    직사각형 x1, y1, x2, y2의 경로는 for문을 통해 해당 경로의 2차원 배열 깂을 모두 건너기 가능한 값으로 바꿔주면 된다.

    외곽선을 인식하는 방법은 2가지가 동시에 충족해야한다.
    
    2번을 통해 구한 2차원 배열에서 둘레 + 모든 직사각형에 대해서 내부에 점이 존재하면 안됨

    이렇게 2가지가 충족하면 된다. 문제의 조건에서는 모든 직사각형 최대 갯수가 4개이므로 시간복잡도에 영향을 주지 않는다.
    
    모든 좌표를 2배로 늘리는 이유는 2번에서 이동 경로를 생성할 때 만약에 길이가 1이면 블로킹 할 수 없기 때문이다.
    예를 들어 (1,1) -> (2, 1) -> (2, 2) -> (1, 2) 로 가야하는 경로가 있다고 가정하자.
    만약 2번 방식으로 이동경로를 구하면 (1,1), (2,1), (2,2), (1,2) 모두 붙어 있기 때문에 우리가 원하는 ㄷ 자 방향이 아닌
    세로 방향으로 접근이 가능해진다. 이거 따로 예외처리해주는 것은 매우 귀찮고 힘든 일이므로 길이를 2배로 늘려서 계산해준다.

"""
from collections import deque
from dataclasses import dataclass


dy = [-1, 0, 1, 0]
dx = [0, -1, 0, 1]


@dataclass
class Rectangle:
    x1: int
    y1: int
    x2: int
    y2: int

    def is_include(self, x, y):
        return self.x1 < x < self.x2 and self.y1 < y < self.y2


def solution(rectangles, character_x, character_y, item_x, item_y):
    character_x *= 2
    character_y *= 2
    item_x *= 2
    item_y *= 2
    rects = []
    board = [[0] * 102 for _ in range(102)]
    create_board_rects(rectangles, rects, board)
    return bfs(rects, board, character_x, character_y, item_x, item_y)


def create_board_rects(rectangles, rects, board):
    for x1, y1, x2, y2 in rectangles:
        rects.append(Rectangle(2 * x1, 2 * y1, 2 * x2, 2 * y2))
        for x in range(x1 * 2, x2 * 2 + 1):
            for y in range(y1 * 2, y2 * 2 + 1):
                board[x][y] = 1


def bfs(rects, board, character_x, character_y, item_x, item_y):
    q = deque([(character_x, character_y, 0)])
    while q:
        x, y, cost = q.popleft()
        if x == item_x and y == item_y:
            return cost // 2
        board[x][y] = 0
        for i in range(4):
            ny = y + dy[i]
            nx = x + dx[i]
            if board[nx][ny] == 1 and not is_in_rectangles(rects, nx, ny):
                q.append((nx, ny, cost + 1))
    print("목표 지점에 도달하지 못함")
    return -1

# 하나라도 rect 안에 Point가 존재하면 True
def is_in_rectangles(rects, x, y):
    for rect in rects:
        if rect.is_include(x, y):
            return True
    return False


print(solution([[1, 1, 7, 4], [3, 2, 5, 5], [4, 3, 6, 9], [2, 6, 8, 8]], 1, 3, 7, 8))