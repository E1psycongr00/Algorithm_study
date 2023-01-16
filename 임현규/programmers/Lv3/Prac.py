from copy import deepcopy
from dataclasses import dataclass
from pprint import pprint
from typing import List


@dataclass
class Matrix:
    arr: List[List[int]]
    row_size: int
    col_size: int

    def __init__(self, row_size, col_size):
        self.row_size = row_size
        self.col_size = col_size
        self.arr = [[0] * col_size for _ in range(row_size)]

    @staticmethod
    def gen(arr):
        instance = Matrix(len(arr), len(arr[0]))
        instance.arr = deepcopy(arr)
        return instance

    def __contains__(self, item):
        for i in range(self.row_size):
            for j in range(self.col_size):
                if self.arr[i][j] == item:
                    return True
        return False

    def __getitem__(self, item):
        if isinstance(item, tuple):
            if len(item) > 2:
                raise Exception()
            if isinstance(item[0], int) and isinstance(item[1], int):
                return self.arr[item[0]][item[1]]
            if isinstance(item[0], slice) or isinstance(item[1], slice):
                if isinstance(item[0], int):
                    return Matrix.gen([row[item[1]] for row in [self.arr[item[0]]]])
                return Matrix.gen([row[item[1]] for row in self.arr[item[0]]])

        raise Exception()

    def rotate90(self):
        self.arr = list(map(list, zip(self.arr[::-1])))

    def check_point(self, y, x):
        return 0 <= y < self.row_size and 0 <= x < self.col_size




array = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
m = Matrix.gen(array)
pprint(m)
m.rotate90()
pprint(m)
print(m.check_point(2, 2))
