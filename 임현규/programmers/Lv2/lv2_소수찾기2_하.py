def is_prime(n):
    if n <= 1:
        return False
    return all(n % i for i in range(2, int(n**0.5) + 1))


def permutation(numbers, picked, r, seen, answer):
    if r < len(numbers):
        x = int("".join(picked))
        if is_prime(x):
            answer.add(x)

    if r == 0:
        return

    for i in range(len(numbers)):
        if i in seen: continue
        picked.append(numbers[i])
        seen.add(i)
        permutation(numbers, picked, r - 1, seen, answer)
        seen.remove(i)
        picked.pop()


def solution(numbers):
    answer = set()
    n = len(numbers)
    permutation(numbers, [], n, set(), answer)
    return len(answer)

print(solution("011"))
