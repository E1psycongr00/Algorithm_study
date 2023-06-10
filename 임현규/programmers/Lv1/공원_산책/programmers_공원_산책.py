dy = {"E": 0, "W": 0, "N": -1, "S": 1}
dx = {"E": 1, "W": -1, "N": 0, "S": 0}


def solution(park, routes):
    y, x = _find_mark(park, "S")
    for route in routes:
        y, x = _move_obstacle(park, route, y, x)
    return y, x


def _move_obstacle(park, route, y, x):
    direction, dist = route.split(" ")
    cy = y
    cx = x
    for _ in range(int(dist)):
        cy, cx = cy + dy[direction], cx + dx[direction]
        if not _is_in_park(park, cy, cx) or park[cy][cx] == "X":
            return y, x
    return cy, cx


def _is_in_park(park, y, x):
    row_size = len(park)
    col_size = len(park[0])
    return 0 <= y < row_size and 0 <= x < col_size


def _find_mark(park, s):
    for i in range(len(park)):
        for j in range(len(park[0])):
            if park[i][j] == s:
                return i, j
    raise Exception("No mark")
