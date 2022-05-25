class Solution:
    def canCompleteCircuit(self, gas: List[int], cost: List[int]) -> int:
        if sum(gas) - sum(cost) < 0:
            return -1
        
        start, total = 0, 0
        for i in range(len(gas)):
            total += gas[i] - cost[i]
            if total < 0: 
                start, total = i+1, 0
        return start