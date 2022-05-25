N = int(input())
dists = list(map(int, input().split()))
costs = list(map(int, input().split()))

# 그리디 알고리즘
cNow = costs[0]
total = 0

for i in range(N-1):
    if cNow < costs[i]:
        total += cNow * dists[i]
    else:
        cNow = costs[i]
        total += cNow * dists[i]
print(total)