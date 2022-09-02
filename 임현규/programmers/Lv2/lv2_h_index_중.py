'''
이분 탐색에 대한 풀이로 이분 탐색을 좀 더 깊게 이해하게 되는 문제
'''
from typing import List
from bisect import bisect_left, bisect_right


def solution(citations: List[int]):
    '''각각 최소 h번 이상 인용된 h개의 논문이 있는 경우 h는 유효한 h-색인
    
    Parameters
    ----------
    citations : List[int]
        목록[int]
    
    Returns
    -------
        h-index보다 크거나 같은 인용 횟수
    
    '''
    answer = 0
    citations.sort()
    for h in range(1, len(citations) + 1):
        if is_vaild(citations, h):
            answer = h
    return answer


def is_vaild(citations: List[int], h: int):
    # citations must be sorted
    right_size = len(citations) - bisect_left(citations, h)
    left_size = bisect_right(citations, h)
    print(left_size, right_size)
    return left_size <= h and right_size >= h


print(solution([0, 1, 1, 4, 6, 7]))
