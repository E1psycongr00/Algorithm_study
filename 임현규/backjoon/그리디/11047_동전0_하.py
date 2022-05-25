import sys
input = sys.stdin.readline


N, K = map(int, input().split())

moneyUnit = []

for _ in range(N):
    x = int(input().rstrip())
    moneyUnit.append(x)

cnt = 0
for unit in reversed(moneyUnit):
    if K >= unit:
        remain, mod = divmod(K, unit)
        K = mod
        cnt += remain
print(cnt)