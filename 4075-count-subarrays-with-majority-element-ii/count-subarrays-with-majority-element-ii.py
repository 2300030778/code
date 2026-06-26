from typing import List
from bisect import bisect_left

class Fenwick:
    def __init__(self, n):
        self.bit = [0] * (n + 1)
    def update(self, i, delta):
        while i < len(self.bit):
            self.bit[i] += delta
            i += i & -i
    def query(self, i):
        s = 0
        while i > 0:
            s += self.bit[i]
            i -= i & -i
        return s
class Solution:
    def countMajoritySubarrays(self, nums: List[int], target: int) -> int:
        prefix = [0]
        cur = 0

        for x in nums:
            cur += 1 if x == target else -1
            prefix.append(cur)

        vals = sorted(set(prefix))
        bit = Fenwick(len(vals))

        ans = 0
        for p in prefix:
            idx = bisect_left(vals, p) + 1
            ans += bit.query(idx - 1)   
            bit.update(idx, 1)

        return ans

        