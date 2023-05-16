def solution(s):
    func_count = 0
    remove_zero_count = 0
    while s != "1":
        remove_zero_count += s.count("0")
        s = bin(s.count("1"))[2:]
    return [func_count, remove_zero_count]