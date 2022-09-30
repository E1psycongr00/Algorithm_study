""" HashMap을 활용한 변형 다익스트라를 사용할 수 없다.
누적된 값이 아니라 현재의 intensity를 비교해서 최소로 저장하기 때문에
heap에서 뽑은 intensity가 항상 결과적으로 최소인 intensity를 보장하지 않는다.
그래서 방문하더라도 intensity가 큰 경우가 발생하기 때문에 업데이트 해주어야 한다.

정석 다익스트라 쓰자....

"""
from collections import defaultdict
from heapq import heappop, heappush


INF = 1_000_000_000


def solution(n, paths, gates, summits):
    graph = make_graph(paths)
    gates = set(gates)
    summits = set(summits)
    visited = dij(n, graph, gates, summits)
    return min(
        [(key, value) for (key, value) in enumerate(visited) if key in summits],
        key=lambda x: (x[1], x[0]),
    )


def make_graph(paths):
    graph = defaultdict(list)
    for i, j, w in paths:
        graph[i].append((w, j))
        graph[j].append((w, i))
    return graph


def dij(n, graph, gates, summits):
    heap = []
    visited_intensity = [INF] * (n + 1)
    for gate in gates:
        heappush(heap, (0, gate))
        visited_intensity[gate] = 0

    while heap:
        intensity, node = heappop(heap)
        if node in summits or intensity > visited_intensity[node]:
            continue

        for next_cost, next_node in graph[node]:
            new_intensity = max(intensity, next_cost)
            if new_intensity < visited_intensity[next_node]:
                visited_intensity[next_node] = new_intensity
                heappush(heap, (new_intensity, next_node))
    return visited_intensity
