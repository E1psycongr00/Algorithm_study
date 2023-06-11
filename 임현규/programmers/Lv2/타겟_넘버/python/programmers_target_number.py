from collections import deque


def solution(numbers, target):
    cnt = 0
    q = deque([(0, 0)])
    while q:
        step, value = q.popleft()
        if step == len(numbers) and value == target:
            cnt += 1
            continue
        for dv in [1, -1]:
            if step + 1 <= len(numbers):
                q.append((step + 1, value + dv * numbers[step]))
    return cnt