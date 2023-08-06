from collections import deque


def solution(bridge_length, weight, truck_weights):
    q_truck = deque(truck_weights)
    bridge = deque([0] * bridge_length, maxlen=bridge_length)
    bridge_weight = 0
    time = 0
    while q_truck or (not q_truck and bridge_weight > 0):
        bridge_weight -= bridge.popleft()
        if q_truck and bridge_weight + q_truck[0] <= weight:
            bridge_weight += q_truck[0]
            bridge.append(q_truck.popleft())
        else:
            bridge.append(0)
        time += 1
    return time

print(solution(2, 10, [7,4,5,6]))