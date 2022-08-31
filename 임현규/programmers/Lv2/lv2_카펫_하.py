def solution(brown:int, yellow:int) -> list[int] | None:
    '''m의 가능한 각 값에 대해
     m이 노란색을 나누면 n = 노란색 // m이고 
     2 * m + 2 * n + 4 == 갈색이면 [max(m,n) + 2, min(m, n) + 2] 을 반환합니다.
    
    Parameters
    ----------
    brown : int
        갈색 타일의 수
    yellow : int
        노란색 타일의 수
    
    Returns
    -------
        두 정수의 목록
    
    '''
    for m in range(1, yellow+1):
        if yellow % m == 0:
            n = yellow // m
            if 2 * m + 2 * n + 4 == brown:
                return [max(m,n) + 2, min(m, n) + 2]