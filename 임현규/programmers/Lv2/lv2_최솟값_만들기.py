def solution(A, B):
    ret = 0
    for a, b in zip(sorted(A), sorted(B, reverse=True)):
        ret += a * b
    return ret