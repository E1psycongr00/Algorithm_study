from collections import defaultdict
from heapq import heappop, heappush
from typing import List, Tuple


def solution(N: int, road: List[List[int]], K: int) -> int:
    '''노드 1에서 그래프의 다른 모든 노드까지의 최단 경로를 찾기 위해 Dijkstra 알고리즘을 사용
    
    Parameters
    ----------
    N : int
        도시의 수
    road : List[List[int]]
        목록[목록[int]]
    K : int
        자동차가 이동할 수 있는 최대 거리
    
    Returns
    -------
        도시 1에서 K 이하 시간에 도달할 수 있는 도시의 수
    
    '''
    graph: defaultdict[int, List[tuple[int, int]]] = defaultdict(list)
    for u, v, c in road:
        graph[u].append((c, v))
        graph[v].append((c, u))

    return dijstra(1, graph, K)


def dijstra(source: int, graph: defaultdict[int, List[Tuple[int, int]]], k: int) -> int:
    pq = [(0, source)]
    visit: defaultdict[int, int] = defaultdict(int)

    while pq:
        dist, node = heappop(pq)
        if node in visit:
            continue
        visit[node] = dist
        for next_dist, next_node in graph[node]:
            if next_node not in visit:
                total_dist = dist + next_dist
                heappush(pq, (total_dist, next_node))
    print(visit)
    return sum(1 for x in visit.values() if x <= k)


print(
    solution(5, [[1, 2, 1], [2, 3, 3], [5, 2, 2], [1, 4, 2], [5, 3, 1], [5, 4, 2]], 3)
)

print(
    solution(
        6,
        [[1, 2, 1], [1, 3, 2], [2, 3, 2], [3, 4, 3], [3, 5, 2], [3, 5, 3], [5, 6, 1]],
        4,
    )
)
