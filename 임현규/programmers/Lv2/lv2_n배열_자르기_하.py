def solution(n, left, right):
    answer = []
    for num in range(left, right+1):
        row, col = getPlane(num, n)
        answer.append(getNumber(row, col))
    return answer


def getNumber(i, j):
    return max(i, j) + 1


def getPlane(x, n):
    row, col = divmod(x, n)
    return row, col

