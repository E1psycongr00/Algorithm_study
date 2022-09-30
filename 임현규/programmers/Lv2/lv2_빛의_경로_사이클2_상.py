""" 
빛의 사이클의 주기를 구하는 방법은 (y, x, 방향) 3차원 배열을 만들어서 해결하는 것이다.
3차원 배열의 경우 방문하지 않은 경우 방문할때까지 노드 규칙에 따라서 돌려주고 돌리는 도중 처음 Visited[r][c][d]
만나는 지점에서 종료를 한다. 그 과정까지 카운트를 하면 cycle 주기를 구할 수 있다.
문제의 경우 반드시 사이클이 존재하는데 node는 모두 특정 방향에 대해서 돌아가도록 설계되어 있다. 그 의미는
이미 방문한 곳을 재방문하기 전 까지가 바로 cycle 주기가 끝나는 시점이라는 것이다.

"""
UP = 0
RIGHT = 1
DOWN = 2
LEFT = 3

directions_derivative = [(-1, 0), (0, 1), (1, 0), (0, -1)]


def rotate(node, d):
    """
    노드가 "R"이면 방향이 시계 방향으로 1만큼 회전하고,
    그렇지 않으면 노드가 "L"이면 방향을 반시계 방향으로 1만큼 회전하고,
    그렇지 않으면 방향을 변경하지 않습니다.

    Args:
      node: 우리가 현재 있는 노드
      d: 방향

    Returns:
      빛이 향하고 있는 방향
    """
    if node == "R":
        return (d + 1) % 4
    elif node == "L":
        return (4 + d - 1) % 4
    else:
        return d


def move(row, col, dir):
    return row + directions_derivative[dir][0], col + directions_derivative[dir][1]


def find_cycle_period(r, c, d, visited, grid):
    """
    빛은 주어진 위치와 방향에서 시작하여 우리가 이미 가본 위치와 방향에 도달할 때까지 계속 움직인다.
    그 시뮬레이션을 돌리면서 주기를 구한다.

    Args:
      r: 열
      c: 열
      d: 방향
      visited: 빛이 이전에 방향 d로 (r, c)에 있었던 경우 방문된[r][c][d]가 true인 부울의 3D 배열
      grid: 그리드

    Returns:
      시작점으로 돌아오는 데 걸리는 단계 수
    """
    cnt = 0
    n = len(grid)
    m = len(grid[0])

    while not visited[r][c][d]:
        visited[r][c][d] = 1
        r, c = move(r, c, d)
        r = (r + n) % n
        c = (c + m) % m
        d = rotate(grid[r][c], d)
        cnt += 1
    return cnt


def solution(grid):
    n = len(grid)
    m = len(grid[0])

    visited = [[[0, 0, 0, 0] for _ in range(m)] for _ in range(n)]
    answer = []
    for r in range(n):
        for c in range(m):
            for dir in range(4):
                if visited[r][c][dir]:
                    continue
                period = find_cycle_period(r, c, dir, visited, grid)
                answer.append(period)
    return sorted(answer)


print(solution(["SL", "LR"]))
