from collections import deque


def solution(progresses, speeds):
    answer = []
    days_left = [(100 - p) // s + ((100 - p) % s > 0) for p, s in zip(progresses, speeds)]
   
    
    while days_left:
        count = 1
        first = days_left.popleft()
        while days_left and days_left[0] <= first:
            days_left.popleft()
            count += 1
        answer.append(count)
    return answer