
def combinations(input_list, pos, r, picked, answer):
    # exit
    if r == 0:
        answer.append(picked[:])
        return
    
    # search
    for i in range(pos, len(input_list)):
        picked.append(input_list[i])
        combinations(input_list, i+1, r-1, picked, answer)
        picked.pop()

answer = []
combinations([1,2,3,4], 0, 2, [], answer)
print(answer)