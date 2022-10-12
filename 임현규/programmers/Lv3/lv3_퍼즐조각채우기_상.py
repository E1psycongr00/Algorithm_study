"""
    구현이 복잡한 문제이다.
    block이라는 클래스를 만들어 회전과 표준화를 이용해서 block을 비교할 수 있게 로직을 짯다.

    로직은 간단하다.
    1. bfs, dfs 를 통해 블록을 얻어온다.
    2. Block을 담고 회전과 표준화를 구현한다.(표준과 회전을 위해서 블럭 내부 좌표들의 최소 최대 y, x 를 구한다)
    3. table과 game 모두 블록을 추출한 후 회전하면서 비교한다. 이 때 한 번 맞춘 짝은 따로 처리해주어야한다.
        내 로직의 경우 table block을 회전해서 조건을 충족하는 경우 더 이상 회전을 수행하지 않았다.
    
    이 문제를 풀면서 얻은 교훈은 복사와 참조를 프로그래밍할 때 주의해서 사용해야 한다는 점이다.
    나는 클래스마다 this를 리턴하는 패턴을 사용했는데 이 때 모두 복사가 일어난다.
    그래서 변수 사이클에 유의해서 코드를 짜 주어야 한다.
"""


from collections import deque
from dataclasses import dataclass
from typing import Set


INF = 1e9

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


@dataclass
class Block:
    min_x: int
    max_x: int
    min_y: int
    max_y: int
    block: Set

    @staticmethod
    def generate(block):
        min_x = min(block, key=lambda x: x[1])[1]
        min_y = min(block, key=lambda x: x[0])[0]
        max_x = max(block, key=lambda x: x[1])[1]
        max_y = max(block, key=lambda x: x[0])[0]
        return Block(min_x, max_x, min_y, max_y, set(block))

    def get_size(self):
        return max(self.max_x - self.min_x, self.max_y - self.min_y)

    def normalize(self):
        new_block = []
        for y, x in self.block:
            new_block.append((y - self.min_y, x - self.min_x))
        return Block.generate(new_block)

    def rotate(self):
        rotated_block = []
        n = self.get_size()
        for y, x in self.block:
            ny = n - 1 - x
            nx = y
            rotated_block.append((ny, nx))
        return Block.generate(rotated_block)

    def __eq__(self, other):
        return self.block == other.block


def bfs(board, y, x, target, visited):
    n = len(board)
    visited.add((y, x))
    q = deque([(y, x)])
    block = [(y, x)]
    while q:
        y, x = q.popleft()
        for i in range(4):
            ny, nx = y + dy[i], x + dx[i]
            if not (0 <= ny < n and 0 <= nx < n):
                continue
            if board[ny][nx] != target:
                continue
            if (ny, nx) in visited:
                continue
            q.append((ny, nx))
            visited.add((ny, nx))
            block.append((ny, nx))
    b = Block.generate(block)
    normalized = b.normalize()
    return normalized


def solution(game_board, table):
    n = len(table)
    puzzle_blocks = []
    puzzle_visited = set()
    game_blocks = []
    game_visited = set()
    for y in range(n):
        for x in range(n):
            if table[y][x] == 1 and (y, x) not in puzzle_visited:
                puzzle_blocks.append(bfs(table, y, x, 1, puzzle_visited))
            if game_board[y][x] == 0 and (y, x) not in game_visited:
                game_blocks.append(bfs(game_board, y, x, 0, game_visited))
    cnt = 0
    for b in puzzle_blocks:
        rotated = b
        for _ in range(4):
            rotated = rotated.rotate().normalize()
            print(rotated)
            if rotated in game_blocks:
                cnt += len(rotated.block)
                game_blocks.remove(rotated)
                break
    return cnt


print(
    solution(
        [
            [1, 1, 0, 0, 1, 0],
            [0, 0, 1, 0, 1, 0],
            [0, 1, 1, 0, 0, 1],
            [1, 1, 0, 1, 1, 1],
            [1, 0, 0, 0, 1, 0],
            [0, 1, 1, 1, 0, 0],
        ],
        [
            [1, 0, 0, 1, 1, 0],
            [1, 0, 1, 0, 1, 0],
            [0, 1, 1, 0, 1, 1],
            [0, 0, 1, 0, 0, 0],
            [1, 1, 0, 1, 1, 0],
            [0, 1, 0, 0, 0, 0],
        ],
    )
)
