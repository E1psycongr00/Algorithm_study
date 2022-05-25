import sys
input = sys.stdin.readline

N , M = map(int, input().split())
board = []

n = min(N, M)

# input값 + zero padding
for i in range(N):
    board.append(list(input().strip()) + [-1] * n)

for i in range(N):
    board.append([-1] * (M + n))

def check(size):
    for i in range(N):
        for j in range(M):
            tmp = board[i][j]
            if tmp == board[i+size-1][j] \
                and tmp == board[i][j+size-1] \
                and tmp == board[i+size-1][j+size-1]:
                return True
    return False

res = 0

for size in range(1, n+1):
    if check(size):
       res = size
print(res*res)