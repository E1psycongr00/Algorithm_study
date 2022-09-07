""" 문제를 분석해보면 숫자 빈도수가 높은 요소가 튜플의 빠른 순서가 된다.
"""
import re
from collections import Counter


def solution(s):
    counter = Counter(map(int,re.findall("\d+", s)))
    return sorted([k for k in counter.keys()], key=lambda x: counter[x], reverse=True)


solution("{{20,111},{111}}")
