from math import log2


def solution(n, a, b):
    '''현재 노드의 범위에 와 b가 모두 포함되어 있으면 답은 현재 노드의 깊이
    
    Parameters
    ----------
    n
        트리의 노드 수
    a
        첫 번째 노드의 인덱스
    b
        두 번째 노드의 인덱스
    
    Returns
    -------
        두 노드의 최소 깊이
    
    '''
    max_depth = int(log2(n))
    answer = []
    divide_and_conquer(1, n, a, b, max_depth, answer)
    return min(answer)


def divide_and_conquer(left, right, a, b, depth, answer):
    if depth == 0:
        return

    mid = (left + right) // 2
    divide_and_conquer(left, mid, a, b, depth - 1, answer)
    divide_and_conquer(mid + 1, right, a, b, depth - 1, answer)

    if left <= a <= right and left <= b <= right:
        answer.append(depth)
