from itertools import permutations


def is_prime(n):
    if n <= 1:
        return False
    return all(n % i for i in range(2, int(n**0.5) + 1))


def solution(numbers):
    answer = set()
    n = len(numbers)
    for r in range(1, n + 1):
        x = set(
            filter(is_prime, map(lambda x: int("".join(x)), permutations(numbers, r)))
        )
        answer.update(x)
    return len(answer)


print(solution("011"))
