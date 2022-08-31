from collections import deque


def solution(number: str, k: int) -> str:
    '''숫자를 반복하고 스택의 마지막 숫자가 현재 숫자보다 작으면 스택에서 꺼내고 k를 감소시킵니다.
    
    스택에서 튀어나온 숫자가 다 떨어지거나 k가 다 떨어질 때까지 이 작업을 수행합니다.
    
    그런 다음 k가 소진될 때까지 스택의 나머지 숫자를 꺼냅니다.
    
    마지막으로 스택을 문자열로 반환합니다.
     
    
    Parameters
    ----------
    number : str
        str = "1924"
    k : int
        제거할 자릿수
    
    Returns
    -------
        주어진 수와 k에서 가능한 가장 큰 수.
    
    '''
    stack:deque[str] = deque()
    for num in number:
        while stack and stack[-1] < num and k > 0:
            stack.pop()
            k -= 1
        stack.append(num)
    while k:
        stack.pop()
        k -= 1
    return "".join(stack)
