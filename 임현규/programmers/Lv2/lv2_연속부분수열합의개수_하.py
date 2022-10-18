def solution(elements):
    answer = set()
    n = len(elements)
    cycles = elements + elements
    for k in range(1, n + 1):
        for i in range(n):
            answer.add(sum(cycles[i:i+k+1]))
    return len(answer)