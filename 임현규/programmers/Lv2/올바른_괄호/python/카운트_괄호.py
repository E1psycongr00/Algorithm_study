OPEN_BRACKET = "("
CLOSE_BRACKET = ")"


def solution(s):
    count = 0
    for ch in s:
        count = countOpenBracket(count, ch)
        count = countCloseBracket(count, ch)
        if isInvalidCount(count):
            return False
    return count == 0


def countOpenBracket(count: int, ch: str) -> int:
    if ch == OPEN_BRACKET:
        return count + 1
    return count


def countCloseBracket(count: int, ch: str) -> int:
    if ch == CLOSE_BRACKET:
        return count - 1
    return count


def isInvalidCount(count) -> bool:
    return count < 0
