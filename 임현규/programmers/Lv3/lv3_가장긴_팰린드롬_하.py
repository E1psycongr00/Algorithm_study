def solution(s: str):
    answer = 0
    if len(s) == 1:
        return 1
    for i in range(len(s) - 1):
        answer = max(answer, check_single_palindrome(i, s), check_double_palindrome(i, s))
    return answer


def is_inside(left, right, s):
    return left >= 0 and right < len(s)


def check_single_palindrome(main_point, s):
    left = main_point
    right = main_point
    while is_inside(left, right, s) and s[left] == s[right]:
        left -= 1
        right += 1
    
    if left != right:
        return right - left + 1 - 2
    
    return right - left + 1


def check_double_palindrome(main_point, s):
    left = main_point
    right = main_point + 1
    while is_inside(left, right, s) and s[left] == s[right]:
        left -= 1
        right += 1
    
    if left != right + 1:
        return right - left + 1 - 2
    return right - left + 1
