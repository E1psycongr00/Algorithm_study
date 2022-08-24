MOD = 1_000_000_007

def solution(n):
    dp = [0] * 5001
    dp[0] = 1
    for i in range(2, n+1, 2):
        k = i - 2
        dp[i] += (3 * dp[k]) % MOD
        k -= 2
        while k >= 0:
            dp[i] += (2 * dp[k]) % MOD
            k -= 2
        dp[i] %= MOD
    return dp[n]