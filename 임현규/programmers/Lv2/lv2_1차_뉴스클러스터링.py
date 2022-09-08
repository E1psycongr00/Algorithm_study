from collections import Counter

VAL = 65536


def solution(str1, str2):
    c1 = Counter(split(str1))
    c2 = Counter(split(str2))
    inner_c = c1 & c2
    sum_c = c1 | c2
    j = 0
    if not c1 and not c2:
        j = 1 * VAL
    else:
        j = sum(inner_c.values()) * 65536 // sum(sum_c.values())
    return j


def split(s):
    s = s.lower()
    tmp = []
    i = 0
    n = len(s)
    while i < n - 1:
        sub_s = s[i : i + 2]
        if str.isalpha(sub_s):
            tmp.append(sub_s)
        i += 1
    return tmp
