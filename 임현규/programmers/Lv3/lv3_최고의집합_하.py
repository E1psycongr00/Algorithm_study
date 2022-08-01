def solution(n, s):
    if n > s:
        return [-1]
    val, mod = divmod(s, n)
    answer = [val] * n
    for i in range(mod):
        answer[i] += 1
    return answer[::-1]