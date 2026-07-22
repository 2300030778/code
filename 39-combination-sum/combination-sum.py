class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        ans = []

        def backtrack(start, curr, total):
            if total == target:
                ans.append(curr[:])
                return
            if total > target:
                return

            for i in range(start, len(candidates)):
                curr.append(candidates[i])
                
                backtrack(i, curr, total + candidates[i])
                curr.pop()

        backtrack(0, [], 0)
        return ans