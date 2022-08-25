DICTIONARY = "AEIOU"
NUMS = [781, 156, 31, 6, 1]


def solution(word):
    cnt = 0
    for i in range(len(word)):
        cnt += DICTIONARY.index(word[i]) * NUMS[i] + 1
    return cnt