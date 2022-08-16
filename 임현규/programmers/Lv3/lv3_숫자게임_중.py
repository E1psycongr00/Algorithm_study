def solution(A, B):
    A.sort()
    B.sort()
    i, j = 0, 0
    cnt = 0
    while i < len(A) and j < len(B):
        if A[i] < B[j]:
            cnt += 1
            i += 1
            j += 1
        else:
            j += 1
    return cnt
