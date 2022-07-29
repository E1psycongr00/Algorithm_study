import math


def solution(n, k):
    input_list = [i for i in range(1, n + 1)]
    answer = []
    k = k-1


    while input_list:
        a, k = divmod(k, math.factorial(n-1))
        answer.append(input_list[a])
        input_list.pop(a)
        n -= 1
    return answer