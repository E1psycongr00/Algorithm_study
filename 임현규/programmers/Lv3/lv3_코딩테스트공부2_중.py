"""
    Set이나 defaultdict 말고 배열로 하니 통과되네....
    배열이 빠르긴하니

    다익스트라로 풀기
"""

from heapq import heappop, heappush

INF = 1e9


def solution(alp, cop, problems):
    max_alp = max(problems, key=lambda x: x[0])[0]
    max_cop = max(problems, key=lambda x: x[1])[1]

    def dij(a, c):
        heap = [(0, a, c)]
        dist = [[INF] * (max_cop + 100) for _ in range(max_alp + 100)]
        dist[a][c] = 0
        while heap:
            cost, alp, cop = heappop(heap)
            if dist[alp][cop] < cost:
                continue

            if alp < max_alp and cost + 1 < dist[alp+1][cop]:
                dist[alp+1][cop] = cost + 1
                heappush(heap, (cost + 1, alp + 1, cop))
            if cop < max_cop and cost + 1 < dist[alp][cop+1]:
                dist[alp][cop+1] = cost + 1
                heappush(heap, (cost + 1, alp, cop + 1))
            for p in problems:
                next_alp = alp + p[2] if alp + p[2] <= max_alp else max_alp
                next_cop = cop + p[3] if cop + p[3] <= max_cop else max_cop
                if alp >= p[0] and cop >= p[1] and cost + p[4] < dist[next_alp][next_cop]:
                    dist[next_alp][next_cop] = cost + p[4]
                    heappush(heap, (cost + p[4], next_alp, next_cop))
        return dist
    if alp >= max_alp and cop >= max_cop:
        return 0
    dist = dij(alp, cop)
    return dist[max_alp][max_cop]
