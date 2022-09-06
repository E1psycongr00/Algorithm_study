from functools import cmp_to_key


def solution(numbers):
    return str(int("".join(sorted(map(str, numbers), key=cmp_to_key(string_cmp), reverse=True))))


def string_cmp(s1, s2):
    return int(s1 + s2) - int(s2 + s1)