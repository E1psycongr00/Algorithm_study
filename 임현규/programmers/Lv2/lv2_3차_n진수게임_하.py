def solution(n, t, m, p):
    nums = make_nums(n, t, m, p)
    answer = []
    for k in range(t):
        idx = (p-1) + m * k
        answer.append(nums[idx])
    return "".join(answer)


def make_nth(val, n, map16):
    answer = []
    if val == 0:
        answer.append(str(val))
        return answer
    
    while val:
        val, mod = divmod(val, n)
        if mod >= 10:
            answer.append(map16[mod])
        else:
            answer.append(str(mod))
    return "".join(answer[::-1])


def make_nums(n, t, m, p):
    i = 0
    nums = []
    map16 = {10: "A", 11: "B", 12: "C", 13: "D", 14: "E", 15: "F"}
    max_idx = (p - 1) + m * (t - 1)
    while True:
        number = make_nth(i, n, map16)
        nums += list(number)
        
        if len(nums) > max_idx:
            break
        i += 1
    return nums
