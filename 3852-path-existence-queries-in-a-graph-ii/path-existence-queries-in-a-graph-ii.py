from typing import List

class Solution:
    def pathExistenceQueries(self, n: int, nums: List[int], maxDiff: int, queries: List[List[int]]) -> List[int]:
       
        arr = sorted((nums[i], i) for i in range(n))

        
        pos = [0] * n

        
        comp = [0] * n

        cid = 0
        for i in range(n):
            pos[arr[i][1]] = i
            if i > 0 and arr[i][0] - arr[i - 1][0] > maxDiff:
                cid += 1
            comp[i] = cid

        
        nxt = [0] * n
        r = 0
        for l in range(n):
            while r + 1 < n and arr[r + 1][0] - arr[l][0] <= maxDiff:
                r += 1
            nxt[l] = r

        
        LOG = 20
        up = [[0] * n for _ in range(LOG)]
        up[0] = nxt[:]

        for k in range(1, LOG):
            for i in range(n):
                up[k][i] = up[k - 1][up[k - 1][i]]

        ans = []

        for u, v in queries:
            u = pos[u]
            v = pos[v]

            if u > v:
                u, v = v, u

           
            if comp[u] != comp[v]:
                ans.append(-1)
                continue

            if u == v:
                ans.append(0)
                continue

            cur = u
            steps = 0

           
            for k in range(LOG - 1, -1, -1):
                if up[k][cur] < v:
                    cur = up[k][cur]
                    steps += 1 << k

            ans.append(steps + 1)

        return ans