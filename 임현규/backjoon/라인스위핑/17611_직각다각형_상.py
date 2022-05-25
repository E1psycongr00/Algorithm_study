import sys

input = sys.stdin.readline

MAX_SIZE = 10 ** 6 + 10

n = int(input())
x = [0] * (n)
y = [0] * (n)

xCnt = [0] * MAX_SIZE
yCnt = [0] * MAX_SIZE

for i in range(n):
    x[i], y[i] = map(int, input().split())
    x[i] += 500001
    y[i] += 500001

x.append(x[0] + 500001)
y.append(y[0] + 500001)

for i in range(n):
    if y[i] == y[i + 1]:
        xmin = min(x[i], x[i + 1])
        xmax = max(x[i], x[i + 1])
        xCnt[xmin] += 1
        xCnt[xmax] -= 1
    elif x[i] == x[i+1]:
        ymin = min(y[i], y[i + 1])
        ymax = max(y[i], y[i + 1])
        yCnt[ymin] += 1
        yCnt[ymax] -= 1

for i in range(1, MAX_SIZE):
    xCnt[i] += xCnt[i - 1]
    yCnt[i] += yCnt[i - 1]

print(max(max(xCnt), max(yCnt)))