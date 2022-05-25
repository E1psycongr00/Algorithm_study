import sys

input = sys.stdin.readline

N, M = map(int, input().split())

res = []
def bT(picked, num, n, r):
    if n == r:
        res.append(picked[:])
    for i in range(num, N+1):
        picked.append(i)
        bT(picked, i+1, n+1, r)
        picked.pop()
        
bT([], 1, 0, M)

for r in res:
    print(" ".join(map(str, r)))
