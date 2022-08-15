OPEN_BRACKET = "("
CLOSE_BRACKET = ")"


def solution(s):
    stack = []
    try:
        for ch in s:
            if ch == CLOSE_BRACKET and stack[-1] == OPEN_BRACKET:
                stack.pop()
            if ch == OPEN_BRACKET:
                stack.append(OPEN_BRACKET)
        if stack:
            raise Exception("실패")
    except Exception as e:
        return False
    
    return True
        