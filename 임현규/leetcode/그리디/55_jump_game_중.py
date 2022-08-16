class Solution:
    def canJump(self, nums):
        
        maximum_reach = 0
        for i in range(len(nums)):
            if i > maximum_reach:
                return False
            maximum_reach = max(i + nums[i], maximum_reach)
        return True