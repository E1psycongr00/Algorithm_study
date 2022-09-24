""" 
1. 조합을 이용해 course 수만큼 담는다.
2. 구한 조합의 갯수를 센다.
3. 최대 갯수를 구한 후 중복되는 값이 있다면 모두 넣는다.(단, 갯수가 2번 이상이여야 한다.)
4. 결과를 사전순으로 정렬해서 출력한다.

"""

from collections import Counter
from itertools import combinations



def solution(orders, course):
    result = []
    for r in course:
        possibility = []
        for order in orders:
            comb = combinations(sorted(order), r)
            possibility += comb
        if not possibility: continue
        counter = Counter(possibility)
        max_value = counter.most_common(1)[0][1]
        result.extend(
            ["".join(key) for key, value in counter.items() if value == max_value and value > 1] 
        )
        result.sort()
    return result


print(solution(["XYZ", "XWY", "WXA"], [2,3,4]))

