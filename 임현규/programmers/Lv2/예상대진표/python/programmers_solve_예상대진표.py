import sys
from math import log2


sys.setrecursionlimit(10**6)


def solution(n,a,b):
    pick = []
    find(pick, log2(n), 0, n, a, b)
    return min(pick)


def find(picked, step, start, end, value1, value2):
    if in_range(start, end, value1) and in_range(start, end, value2):
        picked.append(step)
    if end - start <= 1:
        return
    mid = (start + end) // 2
    find(picked, step -1, start, mid, value1, value2)
    find(picked, step - 1, mid +1, end, value1, value2)
    
    
def in_range(start, end, value):
    return start <= value <= end