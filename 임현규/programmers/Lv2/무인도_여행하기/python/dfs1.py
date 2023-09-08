import sys

sys.setrecursionlimit(10 ** 7)

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]


def solution(maps):
    visited = set()
    rows = len(maps)
    cols = len(maps[0])

    def dfs(y, x):
        if y >= rows or y < 0 or x >= cols or x < 0:
            return 0
        if (y, x) in visited:
            return 0
        if maps[y][x] == 'X':
            return 0
        visited.add((y, x))

        total_sum = int(maps[y][x])
        for k in range(4):
            ny = y + dy[k]
            nx = x + dx[k]
            total_sum = total_sum + dfs(ny, nx)
        return total_sum

    result = []
    for i in range(rows):
        for j in range(cols):
            island = dfs(i, j)
            if island > 0:
                result.append(island)
    return [-1] if len(result) == 0 else sorted(result)