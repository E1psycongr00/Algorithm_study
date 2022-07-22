from collections import defaultdict
from copy import deepcopy

answer = 0
graph = defaultdict(list)


def dfs(current, seen, n_sheep, n_wolf, info, can_go):
    global graph, answer
    if seen[current]: return
    seen[current] = True
    
    if info[current]:
        n_wolf += 1
    else:
        n_sheep += 1
        answer = max(answer, n_sheep)
    
    if n_sheep <= n_wolf:
        return
    
    can_go.extend(graph[current])
    for next in can_go:
        
        dfs(next, deepcopy(seen), n_sheep, n_wolf, info, can_go=[loc for loc in can_go if loc != next])


def solution(info, edges):
    global answer, graph
    # make a Graph
    for u, v in edges:
        graph[u].append(v)
    seen = [False] * len(info)
    dfs(0, seen, 0, 0, info, [])
    return answer

