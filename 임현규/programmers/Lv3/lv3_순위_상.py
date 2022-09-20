INF = 10000


def solution(n, results):
    matrix = [[INF] * n for _ in range(n)]
    for i in range(n):
        matrix[i][i] = 0

    for u, v in results:
        matrix[u - 1][v - 1] = 1

    for k in range(n):
        for i in range(n):
            for j in range(n):
                matrix[i][j] = min(matrix[i][j], matrix[i][k] + matrix[k][j])

    cnt = 0
    for i in range(n):
        row_i = matrix[i]
        col_i = [r[i] for r in matrix]
        sum_of_row_i = sum(1 for _ in filter(lambda x: x > 0 and x < INF, row_i))
        sum_of_col_i = sum(1 for _ in filter(lambda x: x > 0 and x < INF, col_i))
        if sum_of_row_i + sum_of_col_i == n - 1:
            cnt += 1
    return cnt


print(solution(5, [[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]))
