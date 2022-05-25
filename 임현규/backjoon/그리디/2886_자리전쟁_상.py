import sys
from heapq import *
from collections import defaultdict

def savePlane2List(s, p):
    for i in range(R):
        for j in range(C):
            if board[i][j] == 'X':
                people.append((i, j))
            elif board[i][j] == 'L':
                seats.append((i, j))

def getDistance(y1, x1, y2, x2):
    return (y2 - y1) ** 2 + (x2 - x1) ** 2

input = sys.stdin.readline

R, C = map(int, input().split())
board = [list(input().rstrip()) for _ in range(R)]

INF = sys.maxsize
seats, people = [], []      # 좌석 좌표와 people 좌표 담아둘 리스트
heap = []
savePlane2List(seats, people)

# 최소힙에 (거리, 좌석 좌표, 사람 좌표)
for s1, s2 in seats:
    for p1, p2 in people:
        heappush(heap, (getDistance(s1, s2, p1, p2), (s1, s2), (p1, p2)))

# 무한대로 초기화한 이유는 작은 거리만이 담겨야 하기 때문
visitSeatDist = [[INF] * C for _ in range(R)] 
visitSeatFrequency = [[0] * C for _ in range(R)]
visitPerson = [[0] * C for _ in range(R)]

cnt = 0
while heap:
    dist, (seatY, seatX), (personY, personX) = heappop(heap)
    # 방문하지 않은 person이 좌석과의 거리가 기존 좌석 거리보다 같거나 작을 경우
    if visitPerson[personY][personX] == 0 and \
        dist <= visitSeatDist[seatY][seatX]:
            visitSeatDist[seatY][seatX] = dist
            visitPerson[personY][personX] = 1
            visitSeatFrequency[seatY][seatX] += 1
            # 반드시 2일때만 해야 한다. 배틀 좌표는 한 곳인데 >1로 설정하면
            # 배틀 좌표를 여러번 세기 때문이다.
            if visitSeatFrequency[seatY][seatX] == 2:
                cnt += 1
            
print(cnt)