''' 
한 보트에 2명씩 최대한 담으려면 정렬후 Two Pointer를 활용한 풀이.
'''
from typing import List


def solution(people: List[int], limit: int) -> int:
    '''목록을 내림차순으로 정렬한 다음 목록의 양 끝에서 시작하여 가장 무거운 사람과 가장 가벼운 사람을 짝지어 봅니다. 
    그들의 무게의 합이 한계보다 작거나 같으면 우리는 그들을 짝을 지어 다음으로 가장 무겁고 가벼운 사람으로 넘어갈 수 있습니다.
    그들의 무게의 합이 한계보다 크면 우리는 다음으로 무거운 사람에게만 이동할 수 있습니다.
    
    Parameters
    ----------
    people : List[int]
        목록[int] = [70, 50, 80, 50]
    limit : int
        보트가 견딜 수 있는 최대 무게
    
    Returns
    -------
        모든 사람을 나르는 데 필요한 배의 수.
    
    '''
    people.sort(reverse=True)
    cnt: int = 0
    left: int = 0
    right: int = len(people) - 1
    while left <= right:
        if people[left] + people[right] <= limit:
            left += 1
            right -= 1
        else:
            left += 1
        cnt += 1
    return cnt

print(solution([70, 50, 80, 50], 100))
