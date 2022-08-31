from itertools import permutations
from typing import List


def solution(k: int, dungeons: List[List[int]]) -> int:
    """가능한 모든 던전 방문 순서를 시도하고 방문할 수 있는 최대 던전 수를 반환

    Parameters
    ----------
    k : int
        당신이 가지고 있는 에너지의 초기 양
    dungeons : List[List[int]]
        각 목록이 던전인 목록 목록

    Returns
    -------
        클리어 가능한 최대 던전 수

    """
    max_cnt = 0
    for perm in permutations(range(len(dungeons))):
        my_tireness = k
        cnt = 0
        for idx in perm:
            condition_tireness, cost_tireness = dungeons[idx]
            if condition_tireness > my_tireness:
                break
            my_tireness -= cost_tireness
            cnt += 1
        max_cnt = max(max_cnt, cnt)
    return max_cnt
