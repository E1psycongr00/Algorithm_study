import sys
from collections import defaultdict

sys.setrecursionlimit(10 ** 7)


class UnionFind:

    def __init__(self, n):
        self.parents = [i for i in range(n)]

    def find(self, x):
        if self.parents[x] != x:
            self.parents[x] = self.find(self.parents[x])
        return self.parents[x]

    def union(self, x, y):
        parent_x = self.find(x)
        parent_y = self.find(y)
        self.parents[parent_y] = parent_x

    def is_connect(self, x, y):
        return self.find(x) == self.find(y)


def solution(maps):
    rows = len(maps)
    cols = len(maps[0])

    def is_in_map(x, y):
        return 0 <= x < rows and 0 <= y < cols

    def is_number(x, y):
        return maps[x][y] != 'X'

    uf = UnionFind(rows * cols)
    for i in range(rows):
        for j in range(cols):
            node = i * cols + j
            right_node = i * cols + j + 1
            down_node = (i + 1) * cols + j
            if is_in_map(i, j + 1) and is_number(i, j + 1):
                uf.union(node, right_node)
            if is_in_map(i + 1, j) and is_number(i + 1, j):
                uf.union(node, down_node)

    islands = defaultdict(int)
    for i in range(rows):
        for j in range(cols):
            if is_number(i, j):
                islands[uf.find(i * cols + j)] += int(maps[i][j])
    return sorted(islands.values()) if islands else [-1]