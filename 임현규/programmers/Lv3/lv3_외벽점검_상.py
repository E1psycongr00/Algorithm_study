from itertools import permutations

INF = 1_000_000


def solution(n, weak_walls, friends_can_dist):
    """
    친구 거리의 가능한 모든 순열을 시도하고 
    각 순열에 대해 약한 벽의 가능한 모든 시작점을 시도하고 
    각 시작점에 대해 약한 벽이 친구의 거리로 덮일 수 있는지 확인
    
    Args:
      n: 벽의 길이
      weak_walls: [1, 5, 6, 10]
      friends_can_dist: [1, 5, 2, 4]
    
    Returns:
      모든 약한 벽을 덮는 데 필요한 최소 친구 수.
    """
    # cycle_weak_walls은 양방향에서 한 방향으로 만들기 위해 원형 약한 벽 배열을 만듬
    cycle_weak_walls = weak_walls + [n + w for w in weak_walls] 
    answer = INF
    for start_index in range(len(weak_walls)):
        for perm in permutations(friends_can_dist):
            count = count_if_cover_all_weak_walls(start_index, cycle_weak_walls, len(weak_walls), perm)
            answer = min(answer, count)
    return answer if answer != INF else -1


def count_if_cover_all_weak_walls(start_index, cycle_weak_walls, n_weak_walls, friends_list):
    """
    시작 인덱스, 약한 벽 목록, 약한 벽 목록의 길이, 친구의 능력 목록이 필요합니다. 
    약한 벽을 모두 덮는 데 필요한 최소 친구 수를 반환하거나 불가능하면 -1을 반환
    
    Args:
      start_index: 사이클에서 첫 번째 약한 벽의 인덱스
      cycle_weak_walls: 길이를 2배로 늘린 원형 약한 벽 목록
      n_weak_walls: 기존 약한 벽의 길이
      friends_list: 정수 목록, 각 정수는 친구가 커버할 수 있는 거리를 나타냅니다.
    
    Returns:
      모든 약한 벽을 덮는 데 필요한 친구의 수.
    """
    count = 1
    pos = cycle_weak_walls[start_index] + friends_list[count - 1]
    for weak_wall in cycle_weak_walls[start_index: start_index + n_weak_walls]:
        if pos < weak_wall: # 커버를 덮지 못했다면 다음 비교를 위해 count와 pos를 업데이트해준다.
            count += 1
            if count > len(friends_list):
                return INF
            pos = weak_wall + friends_list[count - 1]
    return count


print(solution(12, [1, 5, 6, 10], [1, 2, 3, 4]))
