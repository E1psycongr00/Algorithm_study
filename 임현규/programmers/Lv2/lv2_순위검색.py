''' 여러 개념을 합쳐서 풀어야하는 문제
Hash + 이분탐색을 활용해야 테스트를 통과 가능
'''
from collections import defaultdict
import re
from bisect import bisect_left

enums = {
    0: ["cpp", "java", "python"],
    1: ["backend", "frontend"],
    2: ["junior", "senior"],
    3: ["chicken", "pizza"],
}


def solution(info, queries):
    '''각 쿼리에 대해 쿼리를 만족하는 사람의 수를 찾아 답변에 추가
    
    Parameters
    ----------
    info
        
    queries
        ["자바 백엔드 주니어 피자 150", "파이썬 프론트엔드 시니어 치킨 210", "파이썬 프론트엔드 시니어 치킨 150", "cpp 백엔드 시니어 피자 260", "자바 백엔드
    주니어 치킨 80", "파이썬 백엔드 시니어 치킨 50"]
    
    Returns
    -------
        쿼리 기준을 충족하는 사람의 수
    
    '''
    table = make_table(info)
    answer = []
    for query in queries:
        cnt = 0
        key_and_values = make_keys(query)
        
        for key, val in key_and_values:
            n = len(table[key]) - bisect_left(table[key], val)
            cnt += n
        answer.append(cnt)
    return answer



def make_table(info):
    table = defaultdict(list)
    for record in info:
        split = record.split(" ")
        key = " ".join(split[:-1])
        val = int(split[-1])
        table[key].append(val)
    for key in table.keys():
        table[key].sort()
    return table


def make_keys(query):
    def make_keys(record, step, picked, answer):
        # exit
        if step == 4:
            answer.append((" ".join(picked), int(record[-1])))
            return

        if record[step] == "-":
            for key in enums[step]:
                picked.append(key)
                make_keys(record, step + 1, picked, answer)
                picked.pop()
        else:
            picked.append(record[step])
            make_keys(record, step + 1, picked, answer)
            picked.pop()

    split = re.split("\\sand\\s|\\s", query)
    # assert(all([len(split) == 4, int(split[-1])]), "형식이 맞지 않음")
    answer = []
    make_keys(split, 0, [], answer)
    return answer