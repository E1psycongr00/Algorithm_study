from itertools import combinations
from collections import Counter, defaultdict

UP, DOWN, LEFT, RIGHT = 0, 1, 2, 3
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

dirMap = {
    "S": {
        UP: UP,
        LEFT: LEFT,
        RIGHT:RIGHT,
        DOWN: DOWN
    },
    "L": {
        UP: LEFT,
        DOWN: RIGHT,
        LEFT: DOWN,
        RIGHT: UP
    },
    "R": {
        UP: RIGHT,
        LEFT: UP,
        RIGHT: DOWN,
        DOWN: LEFT
    }
}


def move(y, x, direction, m, n):
    ny = (y + dy[direction] + m) % m
    nx = (x + dx[direction] + n) % n
    return ny, nx


def solution(grid):
    m = len(grid)
    n = len(grid[0])

    visited = [[ [0, 0, 0, 0] for _ in range(n)] for _ in range(m)]
    ans = []

    for i in range(m):
        for j in range(n):
            for k in range(4):
                if visited[i][j][k]: continue
                curY = i
                curX = j
                curDir = k
                cnt = 0

                while not visited[curY][curX][curDir]:
                    visited[curY][curX][curDir] = 1
                    curY, curX = move(curY, curX, curDir, m, n)
                    curNode = grid[curY][curX]

                    curDir = dirMap[curNode][curDir]

                    cnt += 1
                ans.append(cnt)
    return ans

print(solution(["SL", "LR"]))


