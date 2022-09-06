''' 맵 최단거리 => BFS
'''
from collections import defaultdict, deque

dy = [-1, 0, 0, 1]
dx = [0, -1, 1, 0]


def solution(maps):
    return bfs(maps)


def is_safe(n, m, y, x):
    return 0 <= y < n and 0 <= x < m


def bfs(maps):
    n = len(maps)
    m = len(maps[0])
    q = deque([(0, 0)])
    seen = defaultdict(int)
    seen[(0, 0)] = 1
    while q:
        y, x = q.popleft()
        if y == n - 1 and x == m - 1:
            return seen[(y, x)]
        for i in range(4):
            ny = y + dy[i]
            nx = x + dx[i]
            if is_safe(n, m, ny, nx) and maps[ny][nx] == 1 and (ny, nx) not in seen:
                q.append((ny, nx))
                seen[(ny, nx)] = seen[(y, x)] + 1
    return -1

print(solution([[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]))