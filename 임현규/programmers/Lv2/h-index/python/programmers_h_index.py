def solution(citations):
    citations.sort(reverse=True)
    result = 0
    n = len(citations)
    for i in range(n + 1):
        if check(i, citations):
            result = i
    return result


def check(value, citations):
    more_h = 0
    less_h = 0
    for val in citations:
        if val >= value:
            more_h += 1
        if val <= value:
            less_h += 1
    return more_h >= value and less_h <= value