from collections import Counter


def solution(a):
    max_length = 0
    n = len(a)
    frequency = Counter(a)
    for key, val in frequency.most_common(n):
        if val < max_length:
            break
        
        length = find_star_sequence_length(a, key)
        max_length = max(max_length, length)
    return max_length


def find_star_sequence_length(arr, k):
    def contains(arr, idx, k):
        return arr[idx] == k or arr[idx + 1] == k
    
    def not_same_adj(arr, idx):
        return arr[idx] != arr[idx + 1]
    
    idx = 0
    match_cnt = 0
    while idx < len(arr) - 1:
        if contains(arr, idx, k) and not_same_adj(arr, idx):
            match_cnt += 1
            idx += 2
        else:
            idx += 1
    return match_cnt * 2

