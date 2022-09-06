""" 정말 고난이도 문제인데 분기가 많아 실수하기도 쉬워서 어려운 문제.

역추적을 위해서는 5 가지 특징을 가지고 풀어야한다.
    - query를 역순으로 추적해야함.
    - 좌우 위아래는 독립적으로 움직이므로 서로 영향을 끼치지 않음.
    - 범위 확장이 가능함.
    - 현재 위치가 끝점인 경우 범위 확장(ex.. 좌우 추적 인덱스가 맨 왼쪽에 있는데 왼쪽을 호출하는 경우 오른쪽만 범위 확장).
    - 역 추적 도중 인덱스를 초과하는 경우(ex.. 좌우 추적 인덱스가 맨 오른쪽에 있는데 왼쪽 쿼리를 허용 dx 이상 호출한 경우) 도달할수 없으므로 0을 리턴.
"""
LEFT = 0
RIGHT = 1
UP = 2
DOWN = 3


def solution(n, m, y, x, queries):
    '''현재 위치에서 시작하여 각 쿼리의 반대 방향으로 다시 이동한 다음 결과 사각형의 셀 수를 반환
    
    Parameters
    ----------
    n
        행렬의 행 수
    m
        열 수
    y
        시작 셀의 행
    x
        시작 열 번호(0-인덱싱됨)
    queries
        튜플 목록, 각 튜플은 방향과 거리
    
    Returns
    -------
        사각형의 셀 수
    
    '''
    return trace_back(n, m, x, y, queries)


def trace_back(n, m, x, y, queries):
    LEFT_EDGE = 0
    RIGHT_EDGE = m - 1
    TOP_EDGE = 0
    BOTTOM_EDGE = n - 1

    def move_back(dir, start_idx, end_idx, dx):
        if dir == LEFT:
            if start_idx == LEFT_EDGE:  # then expand
                return start_idx, min(end_idx + dx, RIGHT_EDGE)
            return start_idx + dx, min(end_idx + dx, RIGHT_EDGE)

        if dir == UP:
            if start_idx == TOP_EDGE:  # then expand
                return start_idx, min(end_idx + dx, BOTTOM_EDGE)
            return start_idx + dx, min(end_idx + dx, BOTTOM_EDGE)

        if dir == RIGHT:
            if end_idx == RIGHT_EDGE:  # then expand
                return max(start_idx - dx, LEFT_EDGE), end_idx
            return  max(start_idx - dx, LEFT_EDGE), end_idx - dx

        if dir == DOWN:
            if end_idx == BOTTOM_EDGE:  # then expand
                return max(start_idx - dx, TOP_EDGE), end_idx
            return max(start_idx - dx, TOP_EDGE), end_idx - dx


    left_col = right_col = x
    top_row = bottom_row = y

    for direction, dx in reversed(queries):
        if direction in [LEFT, RIGHT]:
            left_col, right_col = move_back(direction, left_col, right_col, dx)
            if left_col > RIGHT_EDGE or right_col < LEFT_EDGE:
                return 0
        else:
            top_row, bottom_row = move_back(direction, top_row, bottom_row, dx)
            if top_row > BOTTOM_EDGE or bottom_row < TOP_EDGE:
                return 0
    
    return (right_col - left_col + 1) * (bottom_row - top_row + 1)

    


print(solution(2, 5, 0, 1, [[3, 1], [2, 2], [1, 1], [2, 3], [0, 1], [2, 1]]))
