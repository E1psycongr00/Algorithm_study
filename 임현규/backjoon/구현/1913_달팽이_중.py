dy = [-1, 0, 1, 0]
dx = [0, 1, 0, -1]

N = int(input())
val = int(input())

board = [[1] * N for _ in range(N) ]

y = N // 2
x = N // 2

idx = 0
num = 2
resultY = 0
resultX = 0
for move in range(1, N):
    for _ in range(2):
        for _ in range(move):
            y = y + dy[idx % 4]
            x = x + dx[idx % 4]
            board[y][x] = num
            if board[y][x] == val:
                resultY = y + 1
                resultX = x + 1
            num += 1
        idx += 1

    
for _ in range(N-1):
    y = y + dy[idx % 4]
    x = x + dx[idx % 4]
    board[y][x] = num
    if board[y][x] == val:
        resultY = y + 1
        resultX = x + 1
    num += 1
    
    
for row in range(N):
    print(" ".join(map(str, board[row])))
print(resultY, resultX)