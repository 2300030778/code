from typing import List

class Solution:
    def arrayRankTransform(self, arr: List[int]) -> List[int]:

        unique = sorted(set(arr))

        rank = {}

        for i, num in enumerate(unique):
            rank[num] = i + 1
        return [rank[num] for num in arr]    


        