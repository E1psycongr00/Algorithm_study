
from collections import deque
from typing import List

pair_table = {
        "]":"[",
        ")":"(",
        "}":"{"
    }

def solution(s: str) -> int:
    '''데크를 왼쪽으로 한 위치 회전하고 회전된 문자열이 유효한 대괄호인지 확인합니다. 그렇다면 카운터를 증가시킵니다.
    
    Parameters
    ----------
    s : str
        str
    
    Returns
    -------
        입력 문자열을 회전하여 만들 수 있는 유효한 대괄호 문자열의 수
    
    '''
    dq: deque[str] = deque(s)
    cnt = 0
    for _ in range(len(s)):
        dq.rotate(-1)
        if is_vaild_bracket("".join(dq)):
            cnt += 1
    return cnt

def is_vaild_bracket(s:str) -> bool:
    stack: List[str] = []
    for ch in s:
        if stack and ch in ["]", "}", ")"]:
            if stack[-1] == pair_table[ch]:
                stack.pop()
            else:
                return False
        else:
            stack.append(ch)
    if stack:
        return False
    return True
    