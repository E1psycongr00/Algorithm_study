from collections import defaultdict
from datetime import datetime, timedelta

PATTERN = "%H:%M"


def solution(n, t, m, timetable):
    bus = datetime.strptime("09:00", PATTERN)
    bus -= timedelta(minutes=t)
    cache = defaultdict(list)
    timetable = list(map(lambda time: datetime.strptime(time, PATTERN), timetable))
    timetable.sort()
    i = 0
    for _ in range(n):
        bus += timedelta(minutes=t)
        while len(cache[bus]) < m and i < len(timetable):
            if timetable[i] > bus:
                break
            cache[bus].append(timetable[i])
            i += 1
    res = find_my_bus_time(cache, m)
    return res.strftime(PATTERN)


def find_my_bus_time(cache, m):
    """
    캐시의 마지막 키가 가득 차면 버스 시간은 목록의 마지막 값에서 1분을 뺀 값이고, 그렇지 않으면 버스 시간은 캐시의 마지막 키
    
    Args:
      cache: datetime 객체 목록의 사전
      m: 타고 싶은 버스의 수
    
    Returns:
      내가 탈 버스 시간
    """
    def is_full(cache, key, m):
        return len(cache[key]) == m

    res = None
    keys = list(cache.keys())
    final_key = keys[-1]
    if is_full(cache, final_key, m):
        res = cache[final_key][-1] - timedelta(minutes=1)
    else:
        res = final_key
    return res
    

