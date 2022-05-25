def solution(distance, rocks, n):
    def condition(x, limit):
        before = 0
        remove = 0
        for rock in rocks:
            if rock - before < x:
                remove += 1
            else:
                before = rock
        if distance - before < x:
            remove += 1
        return remove <= limit
    
    rocks.sort()
    
    lo = 1
    hi = distance + 1
    while lo < hi:
        mid = (lo + hi) // 2
        if condition(mid, n):
            lo = mid + 1
        else:
            hi = mid
    return hi - 1