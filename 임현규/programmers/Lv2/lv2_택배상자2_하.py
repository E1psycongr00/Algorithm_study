"""
    변형된 모노토닉 스택 문제
    모노토닉 스택 문제 패턴과 유사하지만 약간은 다른 패턴
    기존 변곡점 찾기나 수열을 정렬하고자 할 때
    내부 수열을 먼저 정렬하고 마지막에 스택에 집어 넣었다
    그러나 이 문제의 경우 내부에서 먼저 처리하는 것이 아닌
    현재 값의 매칭 여부와 상관없이 없어지고 매칭이 안된다면 
    stack에 들어가기 때문에 stack에 먼저 넣고 스택 처리를 하는 것이다.
"""

def solution(order):
    n = len(order)
    idx = 0
    sub = []
    for i in range(1, n + 1):
        sub.append(i)
        while sub and sub[-1] == order[idx]:
            sub.pop()
            idx += 1
    return idx
