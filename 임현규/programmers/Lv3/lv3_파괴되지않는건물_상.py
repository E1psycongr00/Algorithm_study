from copy import deepcopy
from itertools import chain
PADDING = 1


def solution(board, skill):
    M, N = len(board), len(board[0])
    dp = [[0] * (N + PADDING) for _ in range(M + PADDING)]
    
    for type, r1, c1, r2, c2, degree in skill:
        degree = degree if type == 2 else -degree
        
        dp[r1 + PADDING][c1 + PADDING] += degree
        
        if c2 + 1 < N:
            dp[r1 + PADDING][c2 + 1 + PADDING] -= degree
            
        if c2 + 1 < N and r2 + 1 < M:
            dp[r2 + 1 + PADDING][c2 + 1 + PADDING] += degree
            
        if r2 + 1 < M:
            dp[r2 + 1 + PADDING][c1 + PADDING] -= degree
        
    tmp = deepcopy(dp)
    
    for i in range(M):
        for j in range(N):
            tmp[i + 1][j + 1] += tmp[i][j + 1]
            
    for j in range(N):
        for i in range(M):
            tmp[i + 1][j + 1] += tmp[i + 1][j]
            
    for i in range(M):
        for j in range(N):
            board[i][j] += tmp[i + PADDING][j + PADDING]
    
    return sum(1 for val in chain(*board) if val > 0)