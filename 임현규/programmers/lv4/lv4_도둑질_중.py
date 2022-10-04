def solution(money):
    n = len(money)
    dp1 = [0 for _ in range(n)]
    dp2 = [0 for _ in range(n)]
    
    dp1[0] = money[0]
    dp1[1] = max(money[0], money[1])
    for i in range(2, n - 1):
        dp1[i] = max(dp1[i-1], dp1[i-2] + money[i])
    dp2[0] = 0
    dp2[1] = money[1]
    for i in range(2, n):
        dp2[i] = max(dp2[i-1], dp2[i-2] + money[i])
    
    return max(dp1 + dp2)
