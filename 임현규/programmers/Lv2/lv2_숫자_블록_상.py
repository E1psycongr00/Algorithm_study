from math import isqrt


def solution(begin, end):
    memory = []
    for i in range(begin, end + 1):
        k = 0 if i == 1 else 1
        for j in range(2, isqrt(i) + 1):
            if i % j == 0 and i // j <= 10**7:
                k = i // j
                break
        memory.append(k)
    return memory
