from itertools import combinations_with_replacement


def solution(n, info):
    lion_cases = generate_lion_cases(n)
    info.reverse()
    score = 0
    result = []
    appeach = info
    for lion in lion_cases:
        diff = calculate_score(appeach, lion)
        if diff > score:
            score = diff
            result = lion
            continue
        if diff == score and is_more_lower_score(appeach, lion):
            result = lion
    return list(reversed(result)) if result else [-1]


def is_more_lower_score(appeach, lion):
    for i in range(0, 11):
        if lion[i] > appeach[i]:
            return True
        if lion[i] < appeach[i]:
            return False
    return False


def calculate_score(appeach, lion):
    appeach_score = 0
    lion_score = 0
    for i in range(0, 11):
        if appeach[i] >= lion[i] and appeach[i] > 0:
            appeach_score += i
            continue
        if appeach[i] < lion[i] and lion[i] > 0:
            lion_score += i
    return lion_score - appeach_score


def generate_lion_cases(n):
    result = []
    for comb in combinations_with_replacement([0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10], n):
        temp = [0] * 11
        for c in comb:
            temp[c] += 1
        result.append(temp)
    return result
