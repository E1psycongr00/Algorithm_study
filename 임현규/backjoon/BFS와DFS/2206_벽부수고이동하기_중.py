from collections import deque
import sys


input = sys.stdin.readline

N, M = map(int, input().split())

board = [list(input().rstrip()) for _ in range(N)]




def bfs(board):
    q = deque([(0, 0, 0)])
    visited = [[[0]*2 for _ in range(M)] for _ in range(N)]
    dy = [-1, 0, 1, 0]
    dx = [0, 1, 0, -1]
    visited[0][0][0] = 1
    while q:

        for _ in range(len(q)):
            w, y, x = q.popleft()
            if y == N - 1 and x == M - 1:
                return visited[y][x][w]
            for i in range(4):
                ny = y + dy[i]
                nx = x + dx[i]
                if 0 <= ny < N and 0 <= nx < M:
                    if board[ny][nx] == '0' and visited[ny][nx][w] == 0:
                        q.append((w, ny, nx))
                        visited[ny][nx][w] += visited[y][x][w] + 1
                    elif board[ny][nx] == '1' and w == 0:
                        q.append((w+1, ny, nx))
                        visited[ny][nx][w+1] = visited[y][x][w] + 1
    return -1


result = bfs(board)
print(result)