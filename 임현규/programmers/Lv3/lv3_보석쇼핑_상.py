""" Counter를 매 루프마다 생성하는 것은 매우 비효율적이다. 
편의성을 위해 Counter(a) - Counter(b)와 같은 형태로 연산을 수행하려면 
각각의 객체를 생성해야하는데 시간초과를 할 가능성이 높다.

"""
from collections import Counter


def solution(gems):
    kinds = len(set(gems))
    n = len(gems)
    left = right = 0
    answer = [-1, 1_000_000]
    cache = Counter([gems[left]])
    while left <= right and right < n:
        if len(cache) < kinds:
            right += 1
            if right == n:
                break
            cache[gems[right]] += 1

        elif len(cache) == kinds:
            if answer[1] - answer[0] > right - left:
                answer = [left + 1, right + 1]
            cache[gems[left]] -= 1
            if cache[gems[left]] <= 0:
                del cache[gems[left]]
            left += 1
    return answer


print(solution(["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"]))
