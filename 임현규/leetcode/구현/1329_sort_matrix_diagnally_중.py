"""y = mx + b라 가정 할 때 y - mx = b가 나온다.
y1 - mx1 = b1
y2 - mx2 = b2
y3 - mx3 = b3
가 나오는데 이 때 b1, b2, b3를 dictionary key로 활용해서 리스트 형태로 동일 직선에 있는 배열을 리스트 형태로 넣으면 된다.
dict[b1] = [........]
이때 b1은 y = mx1 + b1 선 위의 점으로 이 직선의 자취라고 봐도 무방하다. 
이를 활용하면 사각형을 마름모 꼴로 회전해서 문제를 해결하는등 여러가지 응용을 할 수 있다.
"""
from collections import defaultdict
from typing import List


def diagonal_sort(mat: List[List[int]]) -> List[List[int]]:
    '''키가 대각선 숫자이고 값이 해당 대각선에 있는 행렬의 요소인 목록 사전을 만듭니다.
     그런 다음 사전에서 값을 정렬하고 행렬에 다시 넣습니다.
    
    Parameters
    ----------
    mat : List[List[int]]
        List[List[int]] - 정렬할 행렬
    
    Returns
    -------
        대각 정렬된 2D 행렬
    '''
    dialog_array: defaultdict[int, List[int]] = defaultdict(list)
    rows: int = len(mat)
    cols: int = len(mat[0])
    for i in range(rows):
        for j in range(cols):
            dialog_array[i - j].append(mat[i][j])
    for k in dialog_array:
        dialog_array[k].sort(reverse=True)
    for i in range(rows):
        for j in range(cols):
            mat[i][j] = dialog_array[i - j].pop()
    return mat

