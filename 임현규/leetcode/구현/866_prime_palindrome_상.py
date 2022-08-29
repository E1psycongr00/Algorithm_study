class Solution:
    def primePalindrome(self, N):
        """
        :type N: int
        :rtype: int
        """
        ndigits = len(str(N))
        while True:
            for x in self.palindromes(ndigits):
                if x >= N and self.isPrime(x):
                    return x
            ndigits += 1
            
    def palindromes(self, n):
        if n == 1:
            for i in range(10):
                yield i
        elif n % 2 == 0:
            d = n // 2
            for i in range(10**(d-1), 10**d):
                s = str(i)
                yield int(s + s[::-1])
        else:
            d = n // 2
            for i in range(10**(d-1), 10**d):
                s = str(i)
                for j in range(10):
                    yield int(s + str(j) + s[::-1])
                    
    def isPrime(self, x):
        return x > 1 and all(x % d for d in range(2, int(x**0.5)+1))