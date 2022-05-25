import sys
input = sys.stdin.readline

DAY2S = 24 * 60 * 60


t1 = list(map(int, input().split(":")))
t2 = list(map(int, input().split(":")))

t1s = t1[0] * 3600 + t1[1] * 60 + t1[2]
t2s = t2[0] * 3600 + t2[1] * 60 + t2[2]

diff = (t2s - t1s + DAY2S) % DAY2S
if diff == 0:
    diff = diff + DAY2S

print("%02d:%02d:%02d" % (diff // 3600, (diff % 3600) // 60, diff % 60))
