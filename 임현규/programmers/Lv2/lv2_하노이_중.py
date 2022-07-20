answer = []


def hanoi(n, source, mid, target):
    if n == 1:
        answer.append([source, target])
        return
    
    hanoi(n - 1, source, target, mid)
    answer.append([source, target])
    hanoi(n - 1, mid, source, target)


def solution(n):
    hanoi(n,1,2,3)
    return answer
