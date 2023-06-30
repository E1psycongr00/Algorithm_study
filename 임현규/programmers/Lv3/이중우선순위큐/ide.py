import heapq


def solution(operations):
    doubleHeap = DoubleHeap()
    for operation in operations:
        op, num = operation.split(" ")
        num = int(num)
        if op == "I":
            doubleHeap.push(num)
        elif op == "D":
            doubleHeap.pop(num)
    return [doubleHeap.pop(1), doubleHeap.pop(-1)]


class DoubleHeap:
    def __init__(self):
        self.min_heap = []
        self.max_heap = []
        self.exist = set()
    
    def push(self, num):
        heapq.heappush(self.min_heap, num)
        heapq.heappush(self.max_heap, -num)
        self.exist.add(num)
    
    def pop(self, num):
        if num == 1:
            while self.max_heap and -self.max_heap[0] not in self.exist:
                heapq.heappop(self.max_heap)
            if self.max_heap:
                value = -heapq.heappop(self.max_heap)
                self.exist.remove(value)
                return value
            return 0
        if num == -1:
            while self.min_heap and self.min_heap[0] not in self.exist:
                heapq.heappop(self.min_heap)
            if self.min_heap:
                value = heapq.heappop(self.min_heap)
                self.exist.remove(value)
                return value
            return 0
        return 0