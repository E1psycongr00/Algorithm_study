from collections import deque


def solution(queue1, queue2):
    target = (sum(queue1) + sum(queue2)) // 2
    q1 = deque(queue1)
    q2 = deque(queue2)
    limit_n = len(q1) + len(q2)
    sum_q1 = sum(q1)
    sum_q2 = sum(q2)
    cnt = 0
    while not end_condition(target, sum_q1, sum_q2) and cnt < 2 * limit_n:
        print(q1, q2, cnt)
        if sum_q1 > target:
            elem = q1.popleft()
            sum_q1 -= elem
            sum_q2 += elem
            q2.append(elem)
        elif sum_q2 > target:
            elem = q2.popleft()
            sum_q2 -= elem
            sum_q1 += elem
            q1.append(elem)
        cnt += 1
    if cnt == 2 * limit_n:
        return -1
    return cnt


def end_condition(target, sum1, sum2):
    return sum1 == target or sum2 == target

print(solution([3, 2, 7, 2]	, [4, 6, 5, 1]	))