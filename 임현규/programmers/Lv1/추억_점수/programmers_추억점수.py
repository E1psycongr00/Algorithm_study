def solution(name, yearning, photo):
    scores = {}
    for n, y in zip(name, yearning):
        scores[n] = y
    return list(sum(scores.get(n, 0) for n in p) for p in photo)

