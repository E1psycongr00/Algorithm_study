from collections import Counter
from itertools import combinations, permutations
import re


def check(id, ban):
    pattern = "^" + ban.replace("*", "\\w") + "$"
    if re.match(pattern, id):
        return True
    return False


def condition(ids, banned_id):
    for id, ban in zip(ids, banned_id):
        if not check(id, ban):
            return False
    return True


def solution(user_id, banned_id):
    perm = permutations(user_id, len(banned_id))
    answer = set()
    for ids in perm:
        if condition(ids, banned_id):
            answer.add(tuple(sorted(ids)))
    print(answer)
    return len(answer)