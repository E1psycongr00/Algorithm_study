def solution(s):
    l = sorted(map(int, s.split(" ")))
    answer = []
    answer.append(str(l[0]))
    answer.append(str(l[-1]))
    return " ".join(answer)