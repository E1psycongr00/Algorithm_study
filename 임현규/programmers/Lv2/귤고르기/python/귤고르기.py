from collections import Counter


def solution(k, tangerine) -> int:
    counter: Counter = Counter(tangerine)
    common: list[tuple[int, int]] = counter.most_common()
    result: int = 0
    idx: int = 0
    while k > 0 and idx < len(common):
        value: int = common[idx][1]
        k -= value
        idx += 1
        result += 1
    return result



result = solution(2, [1, 1, 1, 1, 2, 2, 2, 3])
print(result)