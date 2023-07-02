from functools import lru_cache



def solution(triangle):
    return tr(0, 0, triangle)


def tr(row, col, triangle):
    @lru_cache(maxsize=None)
    def tr_helper(row, col):
        if row == len(triangle) - 1:
            return triangle[row][col]
        return triangle[row][col] + max(tr_helper(row + 1, col), tr_helper(row +1, col + 1))
    return tr_helper(row, col)