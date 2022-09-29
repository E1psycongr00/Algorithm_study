from collections import defaultdict
import sys

sys.setrecursionlimit(10**6)
cnt = 0


def solution(a, edges):
    graph = make_graph(edges)
    dfs(a, graph, set([0]), 0, -1)
    return cnt if not a[0] else -1

def make_graph(edges):
    graph = defaultdict(list)
    for a, b in edges:
        graph[a].append(b)
        graph[b].append(a)
    return graph


def dfs(a, graph, visited, node, before_node):
    """
    깊이 우선 방식으로 그래프를 탐색하고 이동하고 이미 방문했거나 모든 자식 노드를 방문한 경우,
    기억해둔 이전 노드와 현재 노드의 배열을 업데이트, 그리고 value 만큼 카운트해준다. 
    단, 주의점은 value는 절대값이다.
    
    Args:
      a: 값의 배열
      graph: 그래프의 사전
      visited: 방문한 노드 집합
      node: 현재 노드
      before_node: 현재 노드 앞의 노드
    
    Returns:
      void
    """
    global cnt
    if node is None:
        return

    for next_node in graph[node]:
        if next_node not in visited:
            visited.add(next_node)
            dfs(a, graph, visited, next_node, node)

    if before_node == -1:
        return
    # update
    val = a[node]
    a[node] -= val
    a[before_node] += val
    cnt += abs(val)
