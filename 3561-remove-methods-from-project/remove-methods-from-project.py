from typing import List

class Solution:
    def remainingMethods(self, n: int, k: int, invocations: List[List[int]]) -> List[int]:
        graph = [[] for _ in range(n)]

        for u, v in invocations:
            graph[u].append(v)

       
        suspicious = [False] * n
        stack = [k]
        suspicious[k] = True

        while stack:
            node = stack.pop()
            for nei in graph[node]:
                if not suspicious[nei]:
                    suspicious[nei] = True
                    stack.append(nei)

      
        for u, v in invocations:
            if not suspicious[u] and suspicious[v]:
                return list(range(n))

       
        return [i for i in range(n) if not suspicious[i]]