def solution(n):
    answer = 0
    for k in range(1, n + 1, 2):
        if n % k == 0:
            answer += 1
    return answer