''' tuple를 담는 리스트로 바꾸고 순차적으로 순회하며 결고 튜플을 찾기

'''
def solution(s):
    '''문자열에서 모든 튜플을 추출하고 길이별로 정렬한 다음 목록에 아직 없는 경우 각 튜플의 각 요소를 결과 목록에 추가
    
    Parameters
    ----------
    s
        쉼표로 구분된 숫자 문자열
    
    Returns
    -------
        입력 문자열의 고유한 요소 목록
    
    '''
    tuples = extract_tuples(s)
    tuples.sort(key=len)
    result = [tuples[0][0]]
    for i in range(1, len(tuples)):
        result += [k for k in tuples[i] if k not in result]
    return result



def extract_tuples(s):
    splited = s.lstrip("{").rstrip("}").split("},{")
    answer = []
    for ss in splited:
        answer.append(list( int(num) for num in ss.split(",")))
    return answer

print(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"))
