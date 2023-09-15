from collections import deque


class CustomQueue:
    def __init__(self, iterable):
        self.iterable = deque(iterable)
        self.__sum = sum(iterable)

    def get_sum(self):
        return self.__sum

    def append(self, x):
        self.iterable.append(x)
        self.__sum += x

    def pop(self):
        if len(self.iterable) == 0:
            raise IndexError("pop from empty queue")
        x = self.iterable.popleft()
        self.__sum -= x
        return x


def move(q1, q2):
    if q1.get_sum() > q2.get_sum():
        q2.append(q1.pop())
    else:
        q1.append(q2.pop())


def solution(queue1, queue2):
    q1 = CustomQueue(queue1)
    q2 = CustomQueue(queue2)
    cnt = 0
    if q1.get_sum() == q2.get_sum():
        return cnt
    for i in range(len(q1.iterable) + len(q2.iterable) + 2):
        move(q1, q2)
        cnt += 1
        if q1.get_sum() == q2.get_sum():
            return cnt
        if i == len(q1.iterable) + len(q2.iterable) + 1:
            return -1
    return cnt