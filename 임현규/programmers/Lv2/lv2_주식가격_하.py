'''
모노토닉 스택 문제 풀이.
모노토닉 스택은 오름차순 또는 내림차순으로 배열을 정렬하면서 변곡점마다 발생하는 이벤트를 핸들링한다.
'''
def solution(prices: list[int]) -> list[int]:
    '''우리는 하락하는 가격의 지수를 추적하기 위해 스택을 사용합니다.
     스택 맨 위에 있는 가격보다 작은 가격을 만나면 스택을 팝하고 현재 인덱스와 스택 맨 위에 있는 인덱스의 차이로
    결과 배열을 업데이트합니다.
    
    Parameters
    ----------
    prices : list[int]
        목록[int] = [1, 2, 3, 2, 3]
    
    Returns
    -------
        주식 가격이 현재 가격보다 낮아질 때까지의 일 수.
    
    '''
    prices[-1] = 0
    result: list[int] = [0] * len(prices)
    stack: list[int] = []
    for i in range(len(prices)):
        while stack and prices[stack[-1]] > prices[i]:
            update_index: int = stack.pop()
            result[update_index] = i - update_index
        stack.append(i)
    return result


print(solution([1,2,3,4]))