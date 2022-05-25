from collections import defaultdict, namedtuple


def solution(tickets):
    graph = defaultdict(list)
    visited = defaultdict(lambda:defaultdict(int))
    for a, b in tickets:
        graph[a].append(b)
        visited[a][b] += 1
    for key in graph:
        graph[key].sort(reverse=True)
    
    result = []
    
    
    def dfs(u, picked):
        nonlocal result
        if len(picked) == len(tickets)+1:
            result = (picked.copy())
            return
        for v in graph[u]:
            if visited[u][v]:
                visited[u][v] -= 1
                dfs(v, picked+[v])
                visited[u][v] += 1
    
    
    dfs("ICN", ["ICN"])
    return result

