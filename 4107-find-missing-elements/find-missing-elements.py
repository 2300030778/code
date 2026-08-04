class Solution:
    def findMissingElements(self, nums: List[int]) -> List[int]:

        s = set(nums)
        ans = []
        for num in range(min(nums), max(nums) + 1):
            if num not in s:
                ans.append(num)
        return ans         



        