from itertools import combinations
from collections import Counter, defaultdict


def combInOrder(comb, order):
    for c in comb:
        if c not in order:
            return False

    return True


def solution(orders, course):
    res = []
    alphabets = set()
    for order in orders:
        alphabets.update(order)

    
    for num in course:
        counter = defaultdict(int)
        for comb in set(combinations(alphabets, num)):
            for order in orders:
                if combInOrder(comb, order):
                    counter[''.join(sorted(comb))] += 1
        maxVal = 0
        for key, v in sorted(counter.items(), key=lambda x: -x[1]):
            print(key, v)
            if v >= maxVal and v > 1:
                maxVal = v
                res.append(key)
            else:
                break

    return sorted(res)

print(solution(["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"], [2,3,4]))


# a = {"a":2, "b": 3, "ad": 5}
#
# print(sorted(a.items(), key=lambda x: x[1]))