import sys
sys.setrecursionlimit(10**4)
input = sys.stdin.readline




t = int(input())
for _ in range(t):
    cols, rows , k = map(int, input().split())
    land = [[0] * cols for _ in range(rows)]

    def dfs(y, x):
        if y < 0 or y >= rows or x < 0 or x >= cols:
            return
        if land[y][x] == 0:
            return
        land[y][x] = 0
        
        dfs(y+1, x)
        dfs(y, x+1)
        dfs(y-1, x)
        dfs(y, x-1)
        
    for _ in range(k):
        x, y = map(int, input().split())
        land[y][x] = 1
    
    cnt = 0
    for i in range(rows):
        for j in range(cols):
            if land[i][j] == 1:
                dfs(i, j)
                cnt += 1
    print(cnt)