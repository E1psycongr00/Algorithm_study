import sys
input = sys.stdin.readline

def getValue(r, c):
    n = max(abs(r), abs(c))
    last = (2*n+1) ** 2
    
    if r == n:
        return last - (n - c)
    if c == -n:
        return last - 2*n - (n - r)
    if r == -n:
        return last - 4*n - (n + c)
    if c == n:
        return last - 6*n - (n + r)
    
r1, c1, r2, c2 = map(int, input().split())

maxLen = 0
for y in range(r1, r2+1):
    for x in range(c1, c2+1):
        maxLen = max(maxLen, len(str(getValue(y, x))))

output = []

for y in range(r1, r2 + 1):
    for x in range(c1, c2 + 1):
        output.append(f'{getValue(y, x): >{maxLen}} ')
    output.append('\n')

# 소용돌이 출력
sys.stdout.write(''.join(output))