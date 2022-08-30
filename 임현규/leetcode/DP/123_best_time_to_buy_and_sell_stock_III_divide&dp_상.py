''' Divide & conquer (분할정복) 알고리즘을 DP로 최적화한 풀이.

하나의 기준선을 정해 좌, 우 buy & sell 트랜잭션을 나눈다. 그러나 days 마다 일일히 기준선을 나눈다면 트랜잭션 내에서 최대 이익 찾는데 O(N)
그리고 기준선 잡는데 O(N) 해서 O(N^2)의 시간 복잡도를 가진다. 그러나 우리가 buy & sell 한 작업을 수행하는데 최대의 이익을 구할 때 이전에 저장한
값을 테이블로 만들었다. 이것을 뽑아쓰면 O(1)만에 한 트랜잭션의 이익을 구할 수 있다.

왼쪽의 트랜잭션은 0부터 n-1까지 오른쪽으로 순회하면서 최대 이익을
오른쪽 트랜잭션은 n-1부터 0까지 왼쪽으로 순회하면서 최대 이익을 저장한다.
중간에 작은 값이 나오더라도 이전값이 크다면 큰 이익값을 그냥 가져온다.(그 범위 내에 최대 이익이므로)

마지막으로,
i를 돌면서 왼쪽 트랜잭션과 오른쪽 트랜잭션을 더한 값중 최대값을 반환한다. (나눔선 순회해서 좌우 더한 값중 최대값 반환하기)

'''
from typing import List


class Solution:

    def maxProfit(self, prices: List[int]) -> int:
        ''' 배열을 두 부분으로 나누고 왼쪽 부분과 오른쪽 부분의 최대 이익을 찾은 다음 두 부분을 더한 값중 최대값을 찾습니다.
        
        Parameters
        ----------
        prices : List[int]
            목록[int] = [3,3,5,0,0,3,1,4]
        
        Returns
        -------
            주식을 두 번 사고 팔 때 얻을 수 있는 최대 이익.
        
        '''
        left_max_profits = self.init_max_profits_left_transaction(prices)
        right_max_profits = self.init_max_profits_right_transaction(prices)
        profits: List[int] = []
        for i in range(len(prices) - 1):
            profits.append(left_max_profits[i] + right_max_profits[i])
        return max(profits)

    def init_max_profits_left_transaction(self, prices: List[int]):
        left_min_value = prices[0]
        left_max_profits = [0] * len(prices)
        for i, price in enumerate(prices[1:], 1):
            left_max_profits[i] = max(left_max_profits[i - 1], price - left_min_value)
            left_min_value = min(left_min_value, price)
        return left_max_profits
    
    def init_max_profits_right_transaction(self, prices: List[int]):
        n = len(prices)
        right_max_value = prices[-1]
        right_max_profits = [0] * len(prices)
        for i in range(n-2, -1, -1):
            right_max_profits[i] = max(right_max_profits[i+1], right_max_value - prices[i])
            right_max_value = max(right_max_value, prices[i])
        return right_max_profits


print(Solution().maxProfit([1,2,3,4,5]))