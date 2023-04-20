class Solution(object):
    def generateMatrix(self, n):
        matrix = [[0 for _ in range(n)] for _ in range(n)]
        NOT_VISITED = 0
        count = 1
        y, x = 0, 0
        dx, dy = 1, 0
        for _ in range(n**2):
            ny, nx = y + dy, x + dx
            if not self.isInArray(ny, nx, n) or matrix[ny][nx] != NOT_VISITED:
                dx, dy = -dy, dx
                ny, nx = y + dy, x + dx
            matrix[y][x] = count
            count += 1
            y, x = ny, nx
        return matrix
    
    def isInArray(self, y, x, n):
        return 0 <= y < n and 0 <= x < n        