"""
    규칙 찾기 + 스택 활용 문제
    1. 규칙 찾기
        1. 규칙은 110을 추출해서 사전순으로 가장 작은 값을 만들어야 함.
            (110을 제거후 0뒤에 110을 삽입함. 0이 없다면 가장 앞에 삽임함)
    2. 연속적으로 규칙 찾고 삽입하기를 O(N)으로 하기
        1. 무식하게 풀기 불가능
            1. 무식하게 순회하면 O(N^K)이 걸리므로 사용하기 힘들다.(K는 110 횟수)
            2. 단순히 구현하면 문자열 또는 리스트기 때문에 삽입시 O(N)이 걸린다.
            3. O(N^2*K)
        2. 스택을 활용한다. (O(N))
            1. 110의 특성을 살펴보면 1번 규칙대로 한번 수행하면 가장 오른쪽에 위치하는 0은 110 다음이다. 결국 110을 연속해서 뽑아내면 된다.
            2. 110을 연속적으로 뽑아낼때 stack을 활용해서 뽑고 나서도 나중에 110이 완성이 된다면 계속해서 pop한다.
            3. 0 뒤에 110을 모두 붙인다.
"""
def solution(ss):
    result = []
    for s in ss:
        st, cnt = extract_110(s)
        idx = find_right_zero_index(st)
        ret = st[:idx+1] + ['1', '1', '0'] * cnt + st[idx+1:]
        result.append("".join(ret))
    return result


def extract_110(s):
    st = []
    cnt = 0
    for ch in s:
        st.append(ch)
        if len(st) >= 3 and st[-3:] == ['1', '1', '0']:
            st.pop()
            st.pop()
            st.pop()
            cnt += 1
    return st, cnt


def find_right_zero_index(s):
    for i in range(len(s) - 1, -1, -1):
        if s[i] == "0":
            return i
    return -1