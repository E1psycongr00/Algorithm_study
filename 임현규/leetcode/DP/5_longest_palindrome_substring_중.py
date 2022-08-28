class Solution:
    def longestPalindrome(self, s: str) -> str:
        
        def is_palindrome(s, dp, i, j):
            if s[i] == s[j]:
                if j - i < 2:
                    return True
                if dp[i+1][j-1]:
                    return True
            return False
        
        dp = [[False] * len(s) for _ in range(len(s))]
        answer = ""
        for interval in range(len(s)):
            for i in range(len(s) - interval):
                j = i + interval
                if is_palindrome(s,dp, i, j):
                    dp[i][j] = True
                    answer = s[i:j + 1]
        return answer