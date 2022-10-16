"""
    문제 접근 자체는 간단하다
    1. 순서에 따른 순열 생성
    2. 시작 지점과 목표 지점간의 최단거리 bfs 구하기
    3. 그 순열들을 순회하며 해당 번호의 짝들 중 어떻게 경유하느냐에 따라 최소를 시뮬레이션 해보기
        ex) (y, x) -> (y1, x1) -> (y2, x2) 가 최소인가? 아니면 (y, x) -> (y2, x2) -> (y1, x1)
    
    2번의 curl move 구현시 실수가 많아 오래걸렸다. bfs까지 완벽하게 푼다면 시뮬레이션은 상대적으로 쉬웠을 것이다.
"""
from collections import defaultdict, deque
from itertools import permutations


dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

INF = 1e9

def solution(board, r, c):
    rows = len(board)
    cols = len(board[0])
    coords = make_coords(board, rows, cols)
    result = INF
    for p in permutations(coords.keys()):
        result = min(result, simulate(coords, board, p, r, c, 0))
    return result


def make_coords(board, rows, cols):
    coords = defaultdict(list)
    for i in range(rows):
        for j in range(cols):
            if board[i][j] > 0:
                coords[board[i][j]].append((i, j))
    return coords


def get_minimum_distance(board, y1, x1, y2, x2):
    def in_array(ny, nx):
        return 0 <= ny < len(board) and 0 <= nx < len(board[0])

    q = deque([(y1, x1, 0)])
    visited = set()
    visited.add((y1, x1))
    while q:
        y, x, dist = q.popleft()
        if y == y2 and x == x2:
            return dist

        for i in range(4):
            ny = y + dy[i]
            nx = x + dx[i]
            if not in_array(ny, nx):
                continue
            if (ny, nx) in visited:
                continue
            visited.add((ny, nx))
            q.append((ny, nx, dist + 1))
        # curl
        for i in range(4):
            curl_y = y
            curl_x = x
            while (1):
                tmp_y = curl_y + dy[i]
                tmp_x = curl_x + dx[i]
                if not in_array(tmp_y, tmp_x):
                    break
                curl_y = tmp_y
                curl_x = tmp_x
                if board[curl_y][curl_x]: break
            if (curl_y, curl_x) in visited:
                continue
            visited.add((curl_y, curl_x))
            q.append((curl_y, curl_x, dist + 1))
    return -1


def simulate(coords, board, path, y, x, step):
    if step == len(path):
        return 0
    num = path[step]
    y1, x1 = coords[num][0]
    y2, x2 = coords[num][1]
    
    d1 = get_minimum_distance(board, y, x, y1, x1) + get_minimum_distance(board, y1, x1, y2, x2)
    d2 = get_minimum_distance(board, y, x, y2, x2) + get_minimum_distance(board, y2, x2, y1, x1)
    
    board[y1][x1] = board[y2][x2] = 0
    result = min(d1 + simulate(coords, board, path, y2, x2, step + 1), d2 + simulate(coords, board, path, y1, x1, step + 1)) + 2
    board[y1][x1] = board[y2][x2] = num
    return result


print(solution([[1, 0, 0, 3], [2, 0, 0, 0], [0, 0, 0, 2], [3, 0, 1, 0]], 1, 0))

