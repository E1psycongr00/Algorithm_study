from collections import deque


dy = [0, -1, 0, 1]
dx = [-1, 0, 1, 0]

def solution(maps):
    n = len(maps)
    m = len(maps[0])
    dist = [[-1] * m for _ in range(n)]
    q = deque([(0, 0)])
    dist[0][0] = 1
    
    while q:
        y, x = q.popleft()
        if y == n-1 and x == m-1:
            return dist[y][x]
        for i in range(4):
            ny = y + dy[i]
            nx = x + dx[i]
            if not (0 <= ny < n and 0 <= nx < m):
                continue
            if dist[ny][nx] == -1 and maps[ny][nx] == 1:
                q.append((ny, nx))
                dist[ny][nx] = dist[y][x] + 1
    return -1
