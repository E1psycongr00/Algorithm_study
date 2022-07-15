def solution(s:str):
    answer = [' ']
    for ch in s.lower():
        if ch.isalpha() and answer[-1] == ' ':
            ch = ch.upper()
        answer.append(ch)
    return "".join(answer[1:])