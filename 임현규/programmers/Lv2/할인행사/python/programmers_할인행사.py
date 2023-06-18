from collections import Counter


def solution(want, number, discount):
    want_counter = Counter({want[i] : number[i] for i in range(len(want))})
    result = 0
    for i in range(10, len(discount)+1):
        if want_counter == Counter(discount[i-10: i]):
            result += 1
    return result
    