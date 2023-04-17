from functools import cmp_to_key


def solution(sequence, k):
    k_ranges = []

    def find_ranges():
        left = 0
        right = 0
        sub_sequence_sum = 0
        while left < len(sequence):
            if right < len(sequence) and sub_sequence_sum < k:
                sub_sequence_sum += sequence[right]
                right += 1
                continue
            if sub_sequence_sum == k:
                k_ranges.append((left, right - 1))
            sub_sequence_sum -= sequence[left]
            left += 1

    find_ranges()
    k_ranges.sort(key=lambda x: x[0])
    k_ranges.sort(key=cmp_to_key(lambda x, y: x[1] - x[0] - (y[1] - y[0])))
    return k_ranges[0]