def solution(dirs):
    visited = set()
    dx, dy = {'U': 0, 'D': 0, 'R': 1, 'L': -1}, {'U': 1, 'D': -1, 'R': 0, 'L': 0}
    x, y = 0, 0

    for d in dirs:
        newX, newY = x + dx[d], y + dy[d]
        if -5 <= newX <= 5 and -5 <= newY <= 5:
            path = (x, y, newX, newY) if x < newX or y < newY else (newX, newY, x, y)
            visited.add(path)
            x, y = newX, newY

    return len(visited)