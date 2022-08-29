from typing import List


def solution(numbers: List[int]):
    '''입력 목록의 각 숫자에 대해 차이가 2비트 이하인 다음 숫자를 찾습니다.
    
    Parameters
    ----------
    numbers : List[int]
        목록[int]
    
    Returns
    -------
        정수 목록
    
    '''
    result: List[int] = []
    for number in numbers:
        result.append(find_next_number_less_2_bit_difference(number))
    return result


def find_next_number_less_2_bit_difference(number: int):
    '''가장 오른쪽 비트가 0이면 1로 뒤집고, 그렇지 않으면 가장 오른쪽 0비트를 1로, 가장 왼쪽 1비트를 0으로 뒤집습니다.
    
    Parameters
    ----------
    number : int
        차이가 2비트 미만인 다음 숫자를 찾고자 하는 숫자
    
    Returns
    -------
        이진 표현에서 동일한 수의 1비트를 가진 다음 숫자
    
    '''
    k = 1
    if number & k == 0:
        return number ^ 1
    while number & k:
        k = k << 1

    number ^= k
    number ^= k >> 1
    return number


print(solution([14]))
