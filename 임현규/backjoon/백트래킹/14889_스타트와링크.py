import random
import sys

N = int(input())
scores = [list(map(int, input().split())) for _ in range(N)]

def calScore(s, l, scores):
    s1 = 0
    for i in s:
        for j in s:
            s1 += scores[i][j]
    s2 = 0
    for i in l:
        for j in l:
            s2 += scores[i][j]
    return abs(s1 - s2)

global result
result = sys.maxsize
def comb(start:list,idx, n):
    global result, cnt, N
    if n == N // 2:
        link = list(filter(lambda x: x not in start, range(N)))
        print(start, link)
        result = min(result, calScore(start, link, scores))
        return
    
    for i in range(idx, N):
        start.append(i)
        comb(start, i+1, n+1)
        start.pop()

comb([], 0, 0)
print(result)

a = []
