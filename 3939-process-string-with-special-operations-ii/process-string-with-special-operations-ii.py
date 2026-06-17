class Solution:
    def processStr(self, s: str, k: int) -> str:
        n = len(s)
        lens = [0] * (n + 1)

        for i, ch in enumerate(s):
            L = lens[i]
            if 'a' <= ch <= 'z':
                lens[i + 1] = L + 1
            elif ch == '*':
                lens[i + 1] = max(0, L - 1)
            elif ch == '#':
                lens[i + 1] = L * 2
            else:  
                lens[i + 1] = L

        if k >= lens[n]:
            return '.'

        for i in range(n - 1, -1, -1):
            ch = s[i]
            L = lens[i]

            if 'a' <= ch <= 'z':
                if k == L:
                    return ch
            elif ch == '#':
                if L:
                    k %= L
            elif ch == '%':
                k = L - 1 - k
           

        return '.'