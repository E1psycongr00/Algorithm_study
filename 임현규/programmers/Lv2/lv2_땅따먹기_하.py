def solution(land):
    answer = 0

    dp = [[0, 0, 0, 0] for _ in range(len(land))]
    dp[0][0], dp[0][1], dp[0][2], dp[0][3] = land[0][0], land[0][1], land[0][2], land[0][3]
    
    for i in range(1, len(land)):
        for j in range(4):
            for k in range(4):
                if j == k:
                    continue
                dp[i][j] = max(dp[i][j], dp[i-1][k] + land[i][j])
    last_row = len(land) - 1
    return max(dp[last_row])