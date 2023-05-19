from collections import Counter


def solution(s: str):
    replaced = s[2:-2].replace("},{", ",").split(",")
    counter = Counter(map(int, replaced))
    most_freqs = counter.most_common(len(counter))
    return [key for key, value in most_freqs]
