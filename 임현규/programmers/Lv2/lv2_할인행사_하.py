from collections import Counter


def solution(want, number, discount):
    counter = Counter()
    for i in range(len(want)):
        counter[want[i]] = number[i]
    
    cnt = 0
    for i in range(len(discount) - 10 + 1):
        if counter == Counter(discount[i: 10 + i]):
            cnt += 1
    return cnt