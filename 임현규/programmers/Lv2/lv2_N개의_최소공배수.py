import math


def lcm(a, b):
    g = math.gcd(a, b)
    return a * b // g


def solution(arr):
    l = arr[0]
    for i in range(1, len(arr)):
        l = lcm(l, arr[i])
    return l