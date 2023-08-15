def solution(number, k):
    stack = []
    for n in number:
        while stack and stack[-1] < n and k:
            k -= 1
            stack.pop()
        stack.append(n)
    while k:
        stack.pop()
        k -= 1
    return "".join(stack)