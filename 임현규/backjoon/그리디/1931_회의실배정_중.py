import sys

input = sys.stdin.readline

N = int(input())
intervals = []
for _ in range(N):
    s, e = map(int, input().split())
    intervals.append((s, e))
    

intervals.sort(key=lambda x: (x[1], x[0]))
print(intervals)

cnt = 1
beforeEnd = intervals[0][1]

for s, e in intervals[1:]:
    if beforeEnd < s:
        beforeEnd = e
        cnt += 1

print(cnt)