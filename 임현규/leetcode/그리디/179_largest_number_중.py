import functools


class Solution:
    def largestNumber(self, nums) -> str:
        def compare(a, b):
            if a + b < b + a:
                return 1
            else:
                return -1
        str_nums = map(str, nums)
        return "".join(sorted(str_nums, key=functools.cmp_to_key(compare)))