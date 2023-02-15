from typing import List


def solution(msg: str):
    left = 0
    dictionary = ["none"] + [chr(i) for i in range(ord("A"), ord("Z") + 1)]
    answer = []
    while left < len(msg):
        right = left + 1
        update_value = ""
        while match(msg, left, right, dictionary) and right <= len(msg):
            update_value = msg[left: right]
            right += 1
        
        dictionary.append(msg[left: right])
        answer.append(dictionary.index(update_value))
        left = right - 1
    return answer


def match(s: str, left: int, right: int, dictionary: List[int]):
    return s[left:right] in dictionary

