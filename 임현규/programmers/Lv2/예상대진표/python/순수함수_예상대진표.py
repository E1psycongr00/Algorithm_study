import sys
from math import log2


sys.setrecursionlimit(10**6)
INF = 10_000

def solution(n,a,b):
    return find(log2(n), 0, n, a, b)


def find(step, start, end, value1, value2):
    if end == start:
        return INF
    mid = (start + end) // 2
    left = find(step -1, start, mid, value1, value2)
    right = find(step -1, mid+1, end, value1, value2)
    
    result = min(left, right)
    if in_range(start, end, value1) and in_range(start, end, value2):
        return min(step, result)
    return result
    
    
def in_range(start, end, value):
    return start <= value <= end