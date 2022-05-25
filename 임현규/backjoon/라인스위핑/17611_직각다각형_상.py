import sys
from collections import defaultdict
input = sys.stdin.readline

MAX_SIZE = 10 ** 6 + 10

n = int(input())
x = [0] * (n)
y = [0] * (n)

xCnt = defaultdict(int)
yCnt = defaultdict(int)

for i in range(n):
    x[i], y[i] = map(int, input().split())

x.append(x[0])
y.append(y[0])

for i in range(n):
    if y[i] == y[i + 1]:
        xmin = min(x[i], x[i + 1])
        xmax = max(x[i], x[i + 1])
        xCnt[xmin] += 1
        xCnt[xmax] -= 1
    if x[i] == x[i+1]:
        ymin = min(y[i], y[i + 1])
        ymax = max(y[i], y[i + 1])
        yCnt[ymin] += 1
        yCnt[ymax] -= 1

num_dict_x = sorted(xCnt.items(), key=(lambda x:x[0]))
num_dict_y = sorted(yCnt.items(), key=(lambda x:x[0]))

xMax = 0
xTmp = 0
for k, v in num_dict_x:
    xTmp += v
    xMax = max(xMax, xTmp)

yMax = 0
yTmp = 0
for k, v in num_dict_y:
    yTmp += v
    yMax = max(yMax, yTmp)

print(max(xMax, yMax))
