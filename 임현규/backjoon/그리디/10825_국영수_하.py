from dataclasses import dataclass
import sys
input = sys.stdin.readline

n = int(input())

table = [0] * (n)
for i in range(n):
    lines = input().split()
    name = lines[0]
    kor, eng, math = map(int, lines[1:])
    table[i] = (name, kor, eng, math)
    

table.sort(key=lambda x: (-x[1], x[2], -x[3], x[0]))
for t in table:
    print(t[0])

