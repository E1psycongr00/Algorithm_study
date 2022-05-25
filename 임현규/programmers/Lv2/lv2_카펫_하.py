def solution(brown, yellow):
    for i in range(1, yellow+1):
        if yellow % i == 0:
            m = yellow // i
            n = i
            if m * 2 + n * 2 + 4 == brown:
                return [m+2, n+2]