import sys

input = sys.stdin.readline


def condition(wires, x, n):
    cnt = 0
    for wire in wires:
        cnt += (wire // x)
    
    return cnt >= n


K, N = map(int, input().split())
wires = []
for _ in range(K):
    wires.append(int(input()))
    
lo = 0
hi = 10 ** 6 + 1
while lo < hi:
    mid = (lo + hi) // 2
    if condition(wires, mid, N):
        lo = mid + 1
    else:
        hi = mid

print(hi - 1)