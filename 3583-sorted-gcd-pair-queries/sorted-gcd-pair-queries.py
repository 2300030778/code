from typing import List
from bisect import bisect_right

class Solution:
    def gcdValues(self, nums: List[int], queries: List[int]) -> List[int]:
        mx = max(nums)

        freq = [0] * (mx + 1)
        for x in nums:
            freq[x] += 1

       
        cnt = [0] * (mx + 1)
        for d in range(1, mx + 1):
            for multiple in range(d, mx + 1, d):
                cnt[d] += freq[multiple]

        
        exact = [0] * (mx + 1)
        for d in range(mx, 0, -1):
            pairs = cnt[d] * (cnt[d] - 1) // 2
            multiple = 2 * d
            while multiple <= mx:
                pairs -= exact[multiple]
                multiple += d
            exact[d] = pairs

       
        prefix = [0] * (mx + 1)
        for d in range(1, mx + 1):
            prefix[d] = prefix[d - 1] + exact[d]

       
        ans = []
        for q in queries:
            ans.append(bisect_right(prefix, q))
        return ans