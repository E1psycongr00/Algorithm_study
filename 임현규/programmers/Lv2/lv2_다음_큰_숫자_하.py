def solution(n):
    orig = count_bit(n)
    while True:
        n += 1
        if count_bit(n) == orig:
            break
    return n


def count_bit(n):
    cnt = 0
    while n:
        n = n & (n - 1)
        cnt += 1
    return cnt