""" 복잡한 구현을 요구하는 완전 탐색 문제이다.
이 문제의 경우 조건이 굉장히 까다로운데 조건을 어떻게 처리하느냐가 관건이다.

처음에 배열을 이용해 처리하려고 했는데 3차원 배열을 생성해야하고 인접 배열에 조건이 만족하는지 살펴보아야 한다.
이렇게 일부분을 탐색하는 방법은 실수하거나 통과하지 못할 가능성이 크다.

문제 힌트로 List가 주어져있는데 기둥과 보의 정보를 result에 담고 result에 매 삽입 삭제 요청마다 result가
조건에 부합하는지 검사를 해주는 것이 안전하다. 문제 제한 조건을 보면 시간 복잡도는 충분하다.
n = 1000, cmd = 1000
result를 set으로 활용하는 경우 result가 적합한지 검사 -> cmd * 1 -> 1000
모든 cmd 입력에 대해서 => cmd * cmd * 1 = 1_000_000

"""
PILLAR = 0  # 기둥
BEAM = 1  # 보


def is_possible_build(result) -> bool:
    def pillar_condition_1(result, x, y, a):
        # 설치할 기둥의 y좌표가 0인 경우
        return y == 0

    def pillar_condition_2(result, x, y, a):
        # 설치할 기둥 아래에 기둥이 있는 경우
        return y != 0 and (x, y - 1, PILLAR) in result

    def pillar_condition_3(result, x, y, a):
        #  한쪽 끝부분에 보가 있는 경우
        return (x - 1, y, BEAM) in result or (x, y, BEAM) in result

    def beam_condition_1(result, x, y, a):
        # 양쪽 끝부분에 보가 동시에 연결되어 있는 경우
        return (x - 1, y, BEAM) in result and (x + 1, y, BEAM) in result

    def beam_condition_2(result, x, y, a):
        # 한쪽 끝부분에 기둥이 있는 경우
        return (x, y - 1, PILLAR) in result or (x + 1, y - 1, PILLAR) in result

    for x, y, a in result:
        if a == PILLAR:
            if not any(
                [
                    pillar_condition_1(result, x, y, a),
                    pillar_condition_2(result, x, y, a),
                    pillar_condition_3(result, x, y, a),
                ]
            ):
                return False
        if a == BEAM:
            if not any(
                [beam_condition_1(result, x, y, a), beam_condition_2(result, x, y, a)]
            ):
                return False
    return True

def solution(n, build_frames):
    result = set()

    for x, y, a, b in build_frames:
        item = (x, y, a)
        if b:
            result.add(item)
            if not is_possible_build(result):
                result.remove(item)
        elif item in result:
            result.remove(item)
            if not is_possible_build(result):
                result.add(item)
        print(result)
    answer = map(list, result)
    return sorted(answer , key= lambda x: (x[0], x[1], x[2]))

print(solution(5, [[0,0,0,1],[2,0,0,1],[4,0,0,1],[0,1,1,1],[1,1,1,1],[2,1,1,1],[3,1,1,1],[2,0,0,0],[1,1,1,0],[2,2,0,1]]))