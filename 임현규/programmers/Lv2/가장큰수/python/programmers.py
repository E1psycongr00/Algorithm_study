from functools import cmp_to_key


def solution(numbers):
    def compare(s1, s2):
        if (s1 + s2) < (s2 + s1):
            return -1
        if (s1 + s2) == (s2 + s1):
            return 0
        return 1
    num = "".join(sorted(map(str, numbers), key=cmp_to_key(compare), reverse=True))
    striped = num.lstrip("0")
    return "0" if striped == "" else striped


