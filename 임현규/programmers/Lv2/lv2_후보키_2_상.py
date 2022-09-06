""" 파이썬 라이브러리를 최대한 이용한 방법

python에 issubset의 기능은 모두 비교 대상 모두 set이여야 하므로 set으로 형변환 후 비교한다.
set.issubset(target) -> set이 target의 subset인가?
set.issuperset(target) -> set이 target의 superset인가?

Example
-------
s1 = {1,2}
s2 = {1,2,3,4}
s1.issubset(s2) -> true
s2.issuperset(s1) -> true
"""
from itertools import combinations


def is_unique(relations, indexes):
    tmp = set()
    for rel in relations:
        key = " ".join(rel[i] for i in indexes)
        tmp.add(key)
    return len(relations) == len(tmp)


def is_minimum(indexes, answer):
    for ans in answer:
        if ans.issubset(indexes):
            return False
    return True


def push_answer_if_minimum(answer, unique_set):
    for u in unique_set:
        if is_minimum(u, answer):
            answer.append(u)


def solution(relations):
    n = len(relations[0])
    answer = []
    for r in range(1, n + 1):
        comb = combinations(range(n), r)
        unique_set = map(set, filter(lambda x: is_unique(relations, x), comb))
        push_answer_if_minimum(answer, unique_set)
    return len(answer)


print(
    solution(
        [
            ["100", "ryan", "music", "2"],
            ["200", "apeach", "math", "2"],
            ["300", "tube", "computer", "3"],
            ["400", "con", "computer", "4"],
            ["500", "muzi", "music", "3"],
            ["600", "apeach", "music", "2"],
        ]
    )
)
