'''
카데인 알고리즘: 연속된 부분합을 구하기 위한 알고리즘.. DP에서 효율적으로 공간을 사용한 알고리즘이다.
Max Sum SubArray를 구하는 방법은 다음과 같다.
Dp[i] = max(A[i], Dp[i-1] + A[i])
A[i]는 단 하나의 값, Dp[i-1]은 이전까지 최대 부분합이다. 단일 값이 더 크다면 단일값으로 초기화해서 연속된 부분합을 구한다.

바로 이전이 MaxSum SubArray라면 여러개의 공간을 둘 필요 없이 변수 하나에 계속해서 갱신해서 저장해주어도 된다.
'''

from typing import List



class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        '''최대 하위 배열 합계는 
            하위 배열의 합계이거나 요소 자체
        
        Parameters
        ----------
        nums : List[int]
            목록[int]
        
        Returns
        -------
            부분배열의 최대 합
        '''

        sum = 0
        max_sum = -1000000000000
        for num in nums:
            sum = max(sum+num, num)
            max_sum = max(max_sum, sum)
            
        return max_sum