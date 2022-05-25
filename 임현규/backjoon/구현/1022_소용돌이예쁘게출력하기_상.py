import sys


input = sys.stdin.readline

r1, c1, r2, c2 = map(int, input().split())
board = [[0] * (c2 - c1 + 1) for _ in range(r2 - r1 + 1)]

direction = [(0, 1), (-1, 0), (0, -1), (1, 0)]

move = 1
d = 0

y = 0
x = 0
cnt = 1
r = r2 - r1
c = c2 - c1
maxLen = 0


while True:
    # 2 번의 방향 전환
    for _ in range(2):
        # move 번 움직임
        for _ in range(move):
            if r1 <= y <= r2 and c1 <= x <= c2:
                board[y-r1][x-c1] = cnt
            cnt += 1
            y += direction[d][0]
            x += direction[d][1]
        d = (d + 1) % 4
    move += 1
    maxLen = len(str(cnt))
    if (board[0][0] and board[r][0]) and board[r][c] and board[0][c]: break

for i in range(r+1):
    for j in range(c+1):
        print(str(board[i][j]).rjust(maxLen, " "), end= " ")
    print()
    