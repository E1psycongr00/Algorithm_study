MOD  =1_000_000_007

def solution(m, n, puddles):
    dp = [[0] * (m + 1) for _ in range(n + 1)]
    for i in range(1, n+1):
        for j in range(1, m+1):
            if (j, i) in puddles:
                continue
            elif (i == 0 and j > 1) or (j == 0 and i > 1):
                dp[i][j] = 1
            elif (i >0 and j > 0):
                dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + 1
    return dp[n][m] % MOD