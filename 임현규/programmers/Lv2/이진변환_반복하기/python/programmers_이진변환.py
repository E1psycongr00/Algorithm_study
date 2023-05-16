def solution(s):
    func_count = 0
    remove_zero_count = 0
    while(s != "1"):
        none_zero_s = remove_all_zero(s)
        count = len(s) - len(none_zero_s)
        s = to_binary(len(none_zero_s))
        func_count += 1
        remove_zero_count += count
    return [func_count, remove_zero_count]

def remove_all_zero(s):
    temp = []
    for ch in s:
        if ch != "0":
            temp.append(ch)
    return "".join(temp)


def to_binary(number):
    temp = []
    while number > 0:
        carry, mod = divmod(number, 2)
        number = carry
        temp.append(str(mod))
    return "".join(temp)
        