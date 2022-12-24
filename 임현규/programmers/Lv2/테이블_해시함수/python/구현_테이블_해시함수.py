from typing import List


def solution(data: List[List[int]], col: int, row_begin: int, row_end: int) -> int:
    sortedData: List[List[int]] = sorted(data, key=lambda x: (x[col - 1], -x[0]))
    hashCode: int = 0
    for i in range(row_begin, row_end + 1):
        hashCode ^= sum(map(lambda x: x % i, sortedData[i - 1]))
    return hashCode
