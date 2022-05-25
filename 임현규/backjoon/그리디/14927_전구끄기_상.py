import sys

input = sys.stdin.readline

INF = sys.maxsize
dy = [0, 0, 0, -1, 1]
dx = [0, -1, 1, 0, 0]

N = int(input())
orig = []
board = [[0] * N for _ in range(N)]

for i in range(N):
    row = list(map(int, input().split()))
    orig.append(row)


def flip(y, x, arr):
    for i in range(5):
        ny = y + dy[i]
        nx = x + dx[i]
        if 0 <= ny < N and 0 <= nx < N:
            arr[ny][nx] = 0 if arr[ny][nx] else 1
        
            
ans = INF

for k in range(1 << N):
    cnt = 0

    # 배열 초기화
    for i in range(N):
        for j in range(N):
            board[i][j] = orig[i][j]
    
    # 비트 마스크를 이용해서 백트래킹 비슷하게 구현
    for col in range(N):
        if k & (1 << col):
            flip(0, col, board)
            cnt += 1
    
    # 위에만 맞는지 확인하고 탐색
    for row in range(1, N):
        for col in range(N):
            if board[row-1][col] == 1:
                flip(row, col, board)
                cnt += 1
                
    flag = True
    for col in range(N):
        if board[N-1][col] == 1:
            flag = False
            break
    
    if flag:
        ans = min(ans, cnt)
    

print(-1 if ans == INF else ans)

