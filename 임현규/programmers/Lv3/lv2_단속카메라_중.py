def solution(routes):
    routes.sort(key=lambda x:x[1])
    
    cnt = 0
    nowEnd = -30001
    for b, e in routes:
        if nowEnd < b:
            cnt += 1
            nowEnd = e
    return cnt