def solution(progresses, speeds):
    answer = []
    days_left = [(100 - p) // s + ((100 - p) % s > 0) for p, s in zip(progresses, speeds)]
    # 각 작업이 완료되는 날짜를 계산합니다.
    # (100 - p) // s는 작업을 완료하는데 필요한 일수를 계산합니다.
    # ((100 - p) % s > 0)은 나머지가 있는 경우 하루를 더해줍니다.
    # zip(progresses, speeds)는 progresses와 speeds를 묶어서 각 작업의 진도와 개발 속도를 가져옵니다.
    
    while days_left:
        # days_left가 빈 리스트가 될 때까지 반복합니다.
        count = 1
        # 배포될 작업의 개수를 저장할 변수입니다.
        for i in range(1, len(days_left)):
            if days_left[i] > days_left[0]:
                # 첫 번째 작업보다 뒤에 있는 작업이 더 빨리 완료되는 경우
                answer.append(count)
                # 첫 번째 작업부터 count개의 작업이 배포됩니다.
                days_left = days_left[i:]
                # 배포된 작업들을 days_left에서 제거합니다.
                break
            else:
                # 첫 번째 작업보다 뒤에 있는 작업이 더 늦게 완료되는 경우
                count += 1
                # 배포될 작업의 개수를 1 증가시킵니다.
        else:
            # for문이 break되지 않은 경우
            answer.append(count)
            # 모든 작업이 배포됩니다.
            days_left = []
            # days_left를 빈 리스트로 만듭니다.
    
    return answer