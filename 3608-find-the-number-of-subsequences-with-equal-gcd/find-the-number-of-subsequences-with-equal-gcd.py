from typing import List
from math import gcd

class Solution:
    def subsequencePairCount(self, nums: List[int]) -> int:
        MOD = 10 ** 9 + 7
        MAX = 200

       
        g = [[0] * (MAX + 1) for _ in range(MAX + 1)]
        for i in range(MAX + 1):
            for j in range(MAX + 1):
                if i == 0:
                    g[i][j] = j
                elif j == 0:
                    g[i][j] = i
                else:
                    g[i][j] = gcd(i, j)

        dp = [[0] * (MAX + 1) for _ in range(MAX + 1)]
        dp[0][0] = 1

        for x in nums:
            ndp = [row[:] for row in dp]  

            for g1 in range(MAX + 1):
                for g2 in range(MAX + 1):
                    if dp[g1][g2] == 0:
                        continue
                    cur = dp[g1][g2]

                
                    ng1 = g[g1][x]
                    ndp[ng1][g2] = (ndp[ng1][g2] + cur) % MOD

                    
                    ng2 = g[g2][x]
                    ndp[g1][ng2] = (ndp[g1][ng2] + cur) % MOD

            dp = ndp

        ans = 0
        for val in range(1, MAX + 1):
            ans = (ans + dp[val][val]) % MOD

        return ans




        