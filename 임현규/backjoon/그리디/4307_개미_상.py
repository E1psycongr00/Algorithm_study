import sys
input = sys.stdin.readline

for _ in range(int(input())):
    minVal, maxVal = 0, 0
    l, n = map(int, input().split())
    for _ in range(n):
        pos = int(input())
        antMin = min(pos,  l - pos)
        antMax = max(pos, l - pos)
        minVal = max(antMin, minVal)
        maxVal = max(antMax, maxVal)
    print(minVal, maxVal)