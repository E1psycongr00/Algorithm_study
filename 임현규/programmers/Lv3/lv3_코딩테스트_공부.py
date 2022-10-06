"""
    다익스트라로 최대한 최적화 해봤는데 테스트케이스 3이 도저히 통과를 안하고 10번도 통과했다 안했다 한다.
    python 말고 java나 c++로 하면 통과하려나...

    DP를 이용하면 좀더 직관적으로 풀 수 있다. 문제를 보면 req, cop가 학습시 겹치게 되는 부분이 많고 req, cop가 150 이하,
    problems가 100 이하라면 이 3개의 변수를 인자로 DP를 수행할 수 있음을 알 수 있다.

    기본적으로 그냥 트레이닝하는 경우
    dp[cur_alp + 1][cur_cop] = min(dp[cur_alp + 1][cur_cop], dp[cur_alp][cur_cop] + 1)
    dp[cur_alp][cur_cop + 1] = min(dp[cur_alp][cur_cop + 1], dp[cur_alp][cur_cop] + 1)
    
    점화식이 생기고 문제로 트레이닝하는 경우
    next_alp = cur_alp + alp_rwd
    next_cop = cur_cop + cop_rwd
    dp[next_alp][next_cop] = min(dp[next_alp][next_cop], dp[cur_alp][cur_cop] + cost)
    가 성립한다. DP 점화식을 짜는건 생각보다 쉬운데 문제는 경계를 잘 설정해야한다. 런타임 에러만 잘 실경쓰면 된다.
    아래의 코드 경우 max_alp, max_cop 좌표에서 최소값을 리턴하며 max_alp, max_cop보다 alp, cop가 초과하는 경우 
    alp = max_alp, cop = max_cop 로 상한을 두어 문제를 풀었다.

"""
INF = 1_000_000_000


def solution(alp, cop, problems):
    max_alp = max(problems, key=lambda x: x[0])[0]
    max_cop = max(problems, key=lambda x: x[1])[1]
    
    alp = min(alp, max_alp)
    cop = min(cop, max_cop)

    dp = [[INF] * (max_cop + 1) for _ in range(max_alp + 1)]
    dp[alp][cop] = 0

    for cur_alp in range(alp, max_alp + 1):
        for cur_cop in range(cop, max_cop + 1):
            if cur_alp < max_alp:
                dp[cur_alp + 1][cur_cop] = min(
                    dp[cur_alp + 1][cur_cop], dp[cur_alp][cur_cop] + 1
                )
            if cur_cop < max_cop:
                dp[cur_alp][cur_cop + 1] = min(
                    dp[cur_alp][cur_cop + 1], dp[cur_alp][cur_cop] + 1
                )
            for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
                if cur_alp >= alp_req and cur_cop >= cop_req:
                    next_alp = min(cur_alp + alp_rwd, max_alp)
                    next_cop = min(cur_cop + cop_rwd, max_cop)
                    dp[next_alp][next_cop] = min(
                        dp[next_alp][next_cop], dp[cur_alp][cur_cop] + cost
                    )
    return dp[-1][-1]


print(solution(10, 10, [[10, 15, 2, 1, 2], [20, 20, 3, 3, 4]]))
