INF = 987654321
cache = [[INF] * 201 for _ in range(201)]


def solution(matrix_sizes):
    n = len(matrix_sizes)
    answer = matmul(0, len(matrix_sizes) - 1, matrix_sizes)
    print(cache)
    return answer


def matmul(s, e, matrix_sizes):
    if cache[s][e] != INF:
        return cache[s][e]
    
    if s == e:
        return 0
    
    for k in range(s, e):
        left = matmul(s, k, matrix_sizes)
        right = matmul(k + 1, e, matrix_sizes)
        cost = matrix_sizes[s][0] * matrix_sizes[s][1] * matrix_sizes[e][1]
        cache[s][e] = min(cache[s][e], left + right + cost)
    
    return cache[s][e]