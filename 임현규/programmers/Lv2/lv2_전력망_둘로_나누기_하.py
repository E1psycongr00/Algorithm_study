from copy import deepcopy
from collections import defaultdict
import sys

INF = sys.maxsize


def make_graph(wires):
    graph = defaultdict(list)
    for u, v in wires:
        graph[u].append(v)
        graph[v].append(u)
    return graph


def find_difference_in_component(graph, n):
    def dfs(graph, seen, u):
        if u in seen:
            return 0
        seen.add(u)
        cnt = 1
        for v in graph[u]:
            cnt += dfs(graph, seen, v)
        return cnt
    
    seen = set()
    result = []
    for i in range(1, n + 1):
        cnt = dfs(graph, seen, i)
        if cnt:
            result.append(cnt)
    assert(len(result) == 2)
    return abs(result[1] - result[0])


def solution(n, wires):
    answer = INF
    for wire in wires:
        new_wires = deepcopy(wires)
        new_wires.remove(wire)
        graph = make_graph(new_wires)
        answer = min(answer, find_difference_in_component(graph, n))
    return answer
