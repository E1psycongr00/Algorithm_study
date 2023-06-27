from collections import Counter
import re


CONST = 65536


def solution(str1, str2):
    str1 = str1.upper()
    str2 = str2.upper()
    c1 = Counter([str1[i:i+2] for i in range(len(str1) - 1) if str1[i:i+2].isalpha()])
    c2 = Counter([str2[i:i+2] for i in range(len(str2) - 1) if str2[i:i+2].isalpha()])
    intersection = c1 & c2
    union = c1 | c2
    length_intersection = len(list(intersection.elements()))
    length_union = len(list(union.elements()))
    result = 1 * CONST
    if length_union > 0:
        result = length_intersection * CONST // length_union
    return result