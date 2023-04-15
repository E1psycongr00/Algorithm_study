from math import sqrt, floor, ceil


def solution(r1, r2):
    cnt = 0
    for x in range(1, r2 + 1):
        max_y = floor(sqrt(r2 ** 2 - x ** 2))
        min_y = 0 if x > r1 else ceil(sqrt(r1 ** 2 - x ** 2))
        cnt += max_y - min_y + 1
    return cnt * 4
