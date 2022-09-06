''' 완전탐색

그리디 문제라 해서 그리디로 풀었다가 멘탈 터짐.. 첨부터 완전탐색으로 풀껄... 그리디인지 바로 판별 안되는 것 부터 내 실력임을 반성해야함..
완전 탐색의 경우 2^N이라 2 ^ 20 탐색이라 시간 초과일줄 알았는데  "B" * 20 으로 해본 결과 1048575 => 대략 10 ^ 5 밖에 나오지 않음.
완전 탐색이 팩토리얼이 아닌 2^N인 경우 대략 20에서 25 사이 정도는 쓸만하다. 라고 할 수 있겠다.
'''


def move(n, pos, dx):
    return (pos + dx + n) % n


def distance(n, pos, dx, s):
    cnt = 1
    idx = move(n, pos, dx)
    while s[idx] == 0 and cnt < n:
        idx = move(n, idx, dx)
        cnt += 1
    if cnt >= n:
        return -1, None
    return cnt, idx


def convert_string_to_number(s):
    ans = []
    for ch in s:
        ans.append(ord(ch) - ord("A"))
    return ans


def solve(n, pos, s):
    score = min(s[pos], 26 - s[pos])
    s[pos] = 0
    # print(f"solve: n:{n}, pos:{pos}, s:{s}, score: {score}")

    left_dist, left_idx = distance(n, pos, -1, s)
    right_dist, right_idx = distance(n, pos, 1, s)
    if left_dist == -1:
        return score

    score += min(
        solve(n, left_idx, s[:]) + left_dist, solve(n, right_idx, s[:]) + right_dist
    )
    return score


def solution(name):
    pos = 0
    n = len(name)
    s = convert_string_to_number(name)
    return solve(n, pos, s)


# print(solution("BBBBBBBBBBBBBBBBBBBB"))

