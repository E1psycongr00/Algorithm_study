

from pprint import pprint


def solution(key, lock):
    key_size = len(key)
    padding_lock = padding(lock, key_size-1, 1)
    
    for _ in range(4):
        key = rotate_90(key)
        for i in range(len(padding_lock) - key_size + 1):
            for j in range(len(padding_lock) - key_size + 1):
                toggle(i, j, key, padding_lock)
                if check(key_size, padding_lock):
                    return True
                toggle(i, j, key, padding_lock)
    return False

def toggle(y, x, key, lock):
    for i in range(len(key)):
        for j in range(len(key)):
            lock[y + i][x + j] ^= key[i][j]

def check(key_size, lock):
    n = len(lock)
    for i in range(key_size-1, n - key_size + 1):
        for j in range(key_size-1, n - key_size + 1):
            if lock[i][j] == 0:
                return False
    return True

def padding(arr, padding_size, initial_value):
    m = len(arr)
    n = len(arr[0])
    new_row_size = m + 2 * padding_size
    new_col_size = n + 2 * padding_size
    new_arr = [[initial_value] * (new_col_size) for _ in range(new_row_size)]
    for i in range(padding_size, padding_size + m):
        for j in range(padding_size, padding_size + n):
            new_arr[i][j] = arr[i - padding_size][j - padding_size]
    return new_arr

def rotate_90(arr):
    return list(map(list, zip(*arr[::-1])))


print(solution([[0, 0, 0], [1, 0, 0], [0, 1, 1]], [[1, 1, 1], [1, 1, 0], [1, 0, 1]]))