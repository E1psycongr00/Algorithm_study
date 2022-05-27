from itertools import combinations
from collections import Counter, defaultdict


def solution(orders, course):
    answer = []
    for c in course:
        temp = []
        for order in orders:
            combi = combinations(sorted(order), c)
            temp += combi
        counter = Counter(temp)
        if counter and max(counter.values()) > 1:
            answer += [''.join(f) for f in counter if counter[f] == max(counter.values())]
    return sorted(answer)