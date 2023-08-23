class UnionFind:
    def __init__(self, n):
        self.parents = [i for i in range(n)]
    
    def find(self, x):
        if self.parents[x] != x:
            self.parents[x] = self.find(self.parents[x])
        return self.parents[x]
    
    def merge(self, x, y):
        x = self.find(x)
        y = self.find(y)
        if x != y:
            self.parents[y] = x
    
    def isConnect(self, x, y):
        return self.find(x) == self.find(y)

    
def solution(n, computers):
    uf = UnionFind(n)
    count = n
    for i in range(n):
        for j in range(i+1, n):
            if computers[i][j] == 1 and not uf.isConnect(i, j):
                uf.merge(i, j)
                count -= 1
    return count
                