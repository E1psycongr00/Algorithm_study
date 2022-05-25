from heapq import *

class UnionFind:
    def __init__(self, size):
        self.parent = [i for i in range(size)]
        self.rank = [1] * size 

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
                self.parent[y] = x
                self.rank[x] += 1
    
    def isConnected(self, x, y):
        return self.find(x) == self.find(y)
    

# kruskal 
def solution(n, costs):
    heap = []
    uf = UnionFind(n+1)
    for i, j, cost in costs:
        heap.append((cost,i,j))
    
    heapify(heap)
    res = 0
    count = n-1
    while heap and count:
        cost, i, j = heappop(heap)
        if not uf.isConnected(i, j):
            uf.union(i, j)
            res += cost
            count -= 1
    return res