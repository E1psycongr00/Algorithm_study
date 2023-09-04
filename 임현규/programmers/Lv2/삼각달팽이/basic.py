from itertools import chain


dy = [1, 0, -1]
dx = [0, 1, -1]


def is_in_array(y, x, n):
    return 0 <= y < n and 0 <= x <= y


def solution(n):
    triangle = [[0] * (rows + 1) for rows in range(n)]
    total = n * (n + 1) // 2
    dir = 0
    y = -1
    x = 0
    for i in range(1, total + 1):
        ny = y + dy[dir]
        nx = x + dx[dir]
        if not is_in_array(ny, nx, n) or (is_in_array(ny, nx, n) and triangle[ny][nx] != 0):
            dir = (dir + 1) % 3
            ny = y + dy[dir]
            nx = x + dx[dir]
        triangle[ny][nx] = i
        y, x = ny, nx
    return list(chain(*triangle))