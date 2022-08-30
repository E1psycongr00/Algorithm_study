'''카데인 알고리즘과 State Machine을 활용한 DP 풀이

https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/149383/Easy-DP-solution-using-state-machine-O(n)-time-complexity-O(1)-space-complexity

state를 4개로 나누었다.
s1 -> s2 -> s3 -> s4

각 state는 이전 state의 값에 영향을 받는다. state는 이전 state 값에 action을 취한 값과 기존 state 값을 비교해서 스킵할지 갱신할지 결정한다.
s1과 s3는 주식을 사기 때문에 state = max(state, before_state - prices[day])가 된다.
s2와 s4는 주식을 팔기 때문에 state = max(state, before_state + prices[day])가 된다.
이런 풀이가 가능한 이유는 stock이 카데인 알고리즘 형태로 해석이 가능하기 때문이다. 
max subArray 또는 stock을 구할 때 카데인 알고리즘은 하나의 state(transaction)에서만 처리한다. 그 이유는 이전 dp 값만 가지고 처리하는 데 하나의
데이터 공간만 있으면 충분하기 때문이다.

k개의 transaction으로 확장하면 아래와 같이 묶어서 해석도 가능하다.
transaction 1 (s1 -> s2) -> tansaction 2(s3 -> s4)

결론은 카데인 알고리즘을 여러개 묶어서 적용할 때의 DP 문제를 푸는 것과 같다.
'''
from typing import List


class Solution:
    INF = 10000000
    def maxProfit(self, prices: List[int]) -> int:
        '''S1은 주식을 매수한 후 얻을 수 있는 최대 이익이며,
        s2는 주식을 매도한 후 얻을 수 있는 최대 이익입니다.
        s3는 주식을 두 번째로 매수한 후 얻을 수 있는 최대 이익입니다.
        s4는 주식을 두 번째로 팔 때 얻을 수 있는 최대 이익
        
        Parameters
        ----------
        prices : List[int]
            목록[int]
        
        Returns
        -------
            주식을 4번까지 사고팔 때 얻을 수 있는 최대 이익.
        
        '''
        s1, s2, s3, s4 = [-self.INF] * 4
        for price in prices:
            s1 = max(s1, -price)
            s2 = max(s2, s1 + price)
            s3 = max(s3, s2 - price)
            s4 = max(s4, s3 + price)
        return s4

