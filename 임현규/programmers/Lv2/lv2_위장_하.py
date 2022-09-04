from collections import defaultdict
from typing import List

def solution(clothes:List[List[str]]) -> int:
    '''`solution`은 옷 목록을 가져와서 입는 방법의 수를 반환
    
    Parameters
    ----------
    clothes : List[List[str]]
        목록[목록[문자열]]
    
    Returns
    -------
        옷을 입는 방법의 수.
    
    '''
    cloth_type:defaultdict[str, int] = defaultdict(int)
    for _, c_type in clothes:
        cloth_type[c_type] += 1
    
    answer = 1
    for val in cloth_type.values():
        answer *= (val + 1)
    return answer - 1