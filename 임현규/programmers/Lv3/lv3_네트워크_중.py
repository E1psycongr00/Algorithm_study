
class UnionFind:
    def __init__(self, size):
        self.parent=  [i for i in range(size)]
        self.rank = [1 for _ in range(size)]
        
    def find(self, x):
        if x != self.parent[x]:
            self.parent[x] = self.find(self.parent[x])
        return self.parent[x]
    
    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)
        if x != y:
            if self.rank[x] > self.rank[y]:
                self.parent[y] = x
            elif self.rank[x] < self.rank[y]:
                self.parent[x] = y
            else:
                self.rank[x] += 1
                self.parent[y] = x
                
    def isConnected(self, x, y):
        return self.find(x) == self.find(y)

def solution(n, computers):
    edges = []
    for i in range(n):
        for j in range(i+1, n):
            if computers[i][j] == 1:
                edges.append((i, j))
            
    count = n
    uf = UnionFind(n)
    for edge in edges:
        if not uf.isConnected(edge[0], edge[1]):
            uf.union(edge[0], edge[1])
            count -= 1
    return count