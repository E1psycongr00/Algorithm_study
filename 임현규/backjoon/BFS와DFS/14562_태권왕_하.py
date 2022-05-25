import sys
from collections import deque
input = sys.stdin.readline

def bfs(ss, tt):
    q = deque([(ss,tt, 0)])
    while q:
        s, t, n = q.popleft()
        if s == t:
            return n
        if s * 2 <= t + 3:
            q.append((s*2, t+3, n+1))
        q.append((s+1, t, n+1))
    return -1

res = []
for _ in range(int(input())):
    S, T = map(int, input().split())
    res.append(str(bfs(S,T)))
    res.append("\n")

sys.stdout.write(''.join(res))