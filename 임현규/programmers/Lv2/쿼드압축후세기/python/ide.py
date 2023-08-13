def is_same(arr, y0, x0, size):
    for y in range(y0, y0 + size):
        for x in range(x0, x0 + size):
            if arr[y][x] != arr[y0][x0]:
                return False
    return True


def quad_compress(result, arr, y0, x0, size):
    if size == 1:
        result.append(arr[y0][x0])
        return

    if is_same(arr, y0, x0, size):
        result.append(arr[y0][x0])
        return

    half = size // 2
    quad_compress(result, arr, y0, x0, half)
    quad_compress(result, arr, y0, x0 + half, half)
    quad_compress(result, arr, y0 + half, x0, half)
    quad_compress(result, arr, y0 + half, x0 + half, half)


def solution(arr):
    result = []
    quad_compress(result, arr, 0, 0, len(arr))
    return [result.count(0), result.count(1)]