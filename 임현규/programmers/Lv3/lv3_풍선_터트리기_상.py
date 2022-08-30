"""
최대 한번은 작은 숫자를 터트릴 수 있다. 이 부분이 어려운 문제이다. 
"""
from typing import List

INF = 1e+9+1

def solution(a: List[int]):
    '''배열을 왼쪽에서 오른쪽으로, 오른쪽에서 왼쪽으로 반복하고 지금까지 본 가장 작은 값의 인덱스를 표시
    
    Parameters
    ----------
    a : List[int]
        목록[int] = [1, 3, 7, 9, 9]
    
    Returns
    -------
        왼쪽 및 오른쪽 이웃보다 작은 요소의 수
    
    '''
    left_value, right_value = INF, INF
    collect_table = [False] * len(a)
    for i in range(len(a)):
        if a[i] < left_value:
            left_value = a[i]
            collect_table[i] = True
        if a[-1-i] < right_value:
            right_value = a[-1-i]
            collect_table[-1-i] = True
    return sum(collect_table)

print(solution([9, -1, 4]))