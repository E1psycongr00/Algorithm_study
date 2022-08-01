from heapq import heapify, heappop, heappush

def solution(n, works):
    
    max_heap = [-i for i in works]
    heapify(max_heap)
    while n:
        val = heappop(max_heap)
        if val == 0:
            break
        val +=1
        n -=1
        heappush(max_heap, val)
    return sum(i*i for i in max_heap)
    