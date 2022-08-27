from typing import List


class Solution:
    def partition(self, s: str) -> List[List[str]]:
        answer = []
        
        def isInside(s: str, left: int, right: int):
            return left >= 0 and right < len(s)
        
        def isPalindrome(s: str, left: int, right: int) -> bool:
            if not isInside(s, left, right):
                return False
            
            while left <= right:
                if s[left] != s[right]:
                    return False
                left += 1
                right -= 1
            
            return True
        
        def partitioning(s: str, index: int, max_len: int, temp: List):
            if index == max_len:
                answer.append(temp.copy())
                return
            # search
            for i in range(index, max_len):
                if isPalindrome(s, index, i):
                    temp.append(s[index: i + 1])
                    partitioning(s, i + 1, max_len, temp)
                    temp.pop()
        
        partitioning(s, 0, len(s), [])
        return answer