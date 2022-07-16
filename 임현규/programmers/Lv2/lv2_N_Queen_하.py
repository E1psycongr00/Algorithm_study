INVALID_COL = -10000000


def isPossible(rows, x, col):
    n = len(rows)
    for i in range(x):
        if rows[i] == col or abs(rows[i] - col) == abs(x - i):
            return False
    return True


def nQueen(rows, x):
    n = len(rows)
    if x == n:
        return 1
    
    result = 0
    for col in range(n):
        if isPossible(rows, x, col):
            rows[x] = col
            result += nQueen(rows, x + 1)
            rows[x] = INVALID_COL
    return result


def solution(n):
    rows = [INVALID_COL] * n
    answer = nQueen(rows, 0)
    return answer

