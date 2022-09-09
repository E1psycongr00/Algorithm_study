def solution(places):
    answer = []
    for place in places:
        answer.append(solve(place))
    return answer


def solve(place):
    people = []
    for i in range(len(place)):
        for j in range(len(place[0])):
            if place[i][j] == "P":
                people.append((i, j))
    answer = all(dfs(i, j, 2, place, set()) for i, j in people)
    return 1 if answer else 0


def dfs(y, x, step, place, visited):
    if (y, x) in visited:
        return True
    if not (0 <= y < len(place) and 0 <= x < len(place[0])):
        return True
    if place[y][x] == "X":
        return True
    if place[y][x] == "P" and step != 2:
        return False
    if step == 0:
        return True
    visited.add((y, x))
    is_safe = all(
        [
            dfs(y - 1, x, step - 1, place, visited),
            dfs(y + 1, x, step - 1, place, visited),
            dfs(y, x + 1, step - 1, place, visited),
            dfs(y, x - 1, step - 1, place, visited),
        ]
    )
    return is_safe
