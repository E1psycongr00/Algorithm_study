from collections import defaultdict


def solution(name, yearning, photo):
    scores = defaultdict(int)
    for n, y in zip(name, yearning):
        scores[n] = y
    return list(sum(scores[n] for n in p) for p in photo)