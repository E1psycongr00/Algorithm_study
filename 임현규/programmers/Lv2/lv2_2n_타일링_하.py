MOD = 1_000_000_007


def solution(n: int) -> int:
    '''n x 2 직사각형을 타일링하는 방법의 수는 n-1 x 2 직사각형을 타일링하는 방법의 수와 n-2 x 2 직사각형을 타일링하는 방법의 합
    
    Parameters
    ----------
    n : int
        타일의 수
    
    Returns
    -------
        1x2 타일로 2xn 보드를 타일링하는 방법의 수.
    
    '''
    tile = [0] * 60001
    tile[1] = 1
    tile[2] = 2
    for i in range(3, n + 1):
        tile[i] = (tile[i - 1] + tile[i - 2]) % MOD
    return tile[n]
