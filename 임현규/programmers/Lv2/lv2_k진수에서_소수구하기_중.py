from math import isqrt


def make_kth(x, k):
    answer = []
    while x:
        x, mod = divmod(x, k)
        answer.append(str(mod))
    return "".join(answer[::-1])


def is_prime(x):
    if x == 1:
        return False
    if x == 2:
        return True
    
    for i in range(2, isqrt(x) + 1):
        if x % i == 0:
            return False
    return True


def solution(n, k):
    temp = make_kth(n, k).split("0")
    answer = 0
    for s in temp:
        if s == '': continue
        if is_prime(int(s)):
            answer += 1
    return answer


print(solution(110011, 10))
