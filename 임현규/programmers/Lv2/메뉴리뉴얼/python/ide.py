from itertools import combinations
from collections import Counter

def solution(orders, course):
    result = []
    for c in course:
        temp = []
        for order in orders:
            comb = combinations(sorted(order), c)
            temp += comb
        counter = Counter(temp)
        if counter and max(counter.values()) > 1:
            result += [''.join(f) for f in counter if counter[f] == max(counter.values())]
    return sorted(result)