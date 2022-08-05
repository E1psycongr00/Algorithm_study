def lower_bound(left, right, target, cores):
    while left < right:
        mid = (left + right) // 2
        
        expect_value = sum(mid // core for core in cores)
        
        if expect_value >= target:
            right = mid
        else:
            left = mid + 1
    return right


def solution(n, cores):
    n -= len(cores)
    if n <= 0:
        return n
    
    hour = lower_bound(left=1, right=n * max(cores), target=n, cores=cores)
    
    # 약수인 경우에 할당되므로 이전까지 hour 만큼만 n에서 빼준다.
    for core in cores:
        n -= ((hour - 1) // core)
    
    # 남은 부분에서 몇번쨰인지 판별한다.
    for i, core in enumerate(cores):
        if hour % core == 0: # 약수인경우
            n -= 1
        
        if n == 0:
            return i + 1
    return -1
