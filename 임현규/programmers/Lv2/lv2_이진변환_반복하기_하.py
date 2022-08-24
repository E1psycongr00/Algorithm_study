def solution(s: str):
    process_cnt = 0
    removed_zero_cnt = 0
    while s != "1":
        replaced = s.replace("0", "")
        removed_zero_cnt += (len(s) - len(replaced))
        process_cnt += 1
        s = bin(len(replaced))[2:]
    return [process_cnt, removed_zero_cnt]