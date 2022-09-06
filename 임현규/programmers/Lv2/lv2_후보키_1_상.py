''' Combinations + bit 연산 활용
'''
def get_number_of_case(n, pos, r, picked, answer):
    # exit
    if r == 0:
        answer.append(picked)
        return

    # search
    for i in range(pos, n):
        picked ^= 1 << i
        get_number_of_case(n, i + 1, r - 1, picked, answer)
        picked ^= 1 << i


def is_unique(relation, pick):
    n = len(relation[0])
    tmp = set()
    indexes = [i for i in range(n) if ((1 << i) & pick)]
    for rel in relation:
        key = " ".join([rel[idx] for idx in indexes])
        if key in tmp:
            return False
        tmp.add(key)
    return True


def is_minimum(relation, ans, cache):
    n = len(relation[0])
    for c in cache:
        if c & ans == c:
            return False
    return True


def solution(relation):
    '''가능한 모든 열 조합을 생성하고 고유성하고 최소성을 만족하는지 확인
    
    Parameters
    ----------
    relation
        데이터의 2D 배열
    
    Returns
    -------
        관계의 고유 키 수
    
    '''
    n = len(relation[0])
    cache = []
    for r in range(1, n + 1):
        answer = []
        get_number_of_case(n=n, pos=0, r=r, picked=0, answer=answer)
        for ans in answer:
            if is_unique(relation, ans) and is_minimum(relation, ans, cache):
                cache.append(ans)
    return len(cache)
