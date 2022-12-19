from typing import List


def solution(phone_book: List[str]):
    '''각 전화번호에 대해 해당 전화번호의 접두사가 전화번호부에 있는지 확인하십시오.
     그렇다면 False를 반환합니다. 그렇지 않으면 True를 반환
    
    Parameters
    ----------
    phone_book : List[str]
        목록[문자열]
    
    Returns
    -------
        참 또는 거짓
    
    '''
    cache = set(phone_book)
    for phone in phone_book:
        tmp = ""
        for ch in phone:
            tmp += ch
            if tmp in cache and tmp != phone:
                return False
    return True