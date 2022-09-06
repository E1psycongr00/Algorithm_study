from collections import deque
from dataclasses import dataclass


@dataclass(frozen=True)
class Item:
    priority: int
    index: int


def make_items(priorities):
    answer = []
    for i, priority in enumerate(priorities):
        answer.append(Item(priority, i))
    return answer


def solution(priorities, location):
    '''우선 순위와 인덱스가 있는 각 항목의 목록을 만든 다음 목록에서
     우선 순위가 가장 높은 항목을 팝하고 위치와 일치하는 인덱스를 가진 항목을 찾을 때까지 반복
    
    Parameters
    ----------
    priorities
        [2, 1, 3, 2]
    location
        인쇄할 문서의 위치
    
    Returns
    -------
        해당 위치의 항목이 인쇄되기 전에 인쇄된 항목 수
    
    '''
    items = make_items(priorities)
    q = deque(items)
    max_priority = max(priorities)
    cnt = 0
    while q:
        if q[0].priority == max_priority:
            item = q.popleft()
            cnt += 1
            max_priority = max(q, key=lambda x: x.priority).priority if q else -1
            if item.index == location:
                break
            continue
        q.rotate(-1)
    return cnt


print(solution([1, 1, 9, 1, 1, 1], 0))
