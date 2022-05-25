def solution(citations):
    N = len(citations)
    answer = 0
    citations.sort()
    for i in range(N):
        h = N - i
        if citations[i] >= h:
            answer = h
            break
    return answer