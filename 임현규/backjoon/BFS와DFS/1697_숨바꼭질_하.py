from collections import deque


def bfs(source, destination):
    q = deque([source])
    time = 0
    visited = [0] * 100001
    visited[source] = 0
    while q:
        for _ in range(len(q)):
            node = q.popleft()
            if node == destination:
                return time
            if node -1 >= 0 and not visited[node - 1]:
                q.append(node - 1)
                visited[node - 1] = 1
            if node + 1 <= 100000 and not visited[node + 1]:
                q.append(node + 1)
                visited[node + 1] = 1
            if node * 2 <= 100000 and not visited[node * 2]:
                visited[node * 2] = 1
                q.append(node * 2)
        time += 1
    return -1


N, K = map(int, input().split())
print(bfs(N, K))