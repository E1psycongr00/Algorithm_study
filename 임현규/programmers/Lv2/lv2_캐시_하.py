from collections import deque

EXECUTETIME_WHEN_CACHE_HIT = 1
EXECUTETIME_WHEN_CACHE_MISS = 5

def solution(cacheSize, cities):
    cache = deque(maxlen=cacheSize)
    time = 0
    for city in cities:
        city = city.lower()
        if city in cache:
            cache.remove(city)
            cache.appendleft(city)
            time += EXECUTETIME_WHEN_CACHE_HIT
        else:
            cache.appendleft(city)
            time += EXECUTETIME_WHEN_CACHE_MISS
    return time