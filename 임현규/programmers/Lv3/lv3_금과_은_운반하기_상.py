""" 조건 범위가 매우 큰 것으로 보아 그리디 아니면 이분 탐색 접근을 해야 함을 알 수 있다.
    이분 탐색이 가능한 이유는 시간을 기준으로 생각해보면 다음과 같다.
    요구하는 금 a와 은 b 를 만족하도록 필요한 최소 시간을 구하여라
    ==> 특정 시간일 때 금 a와 은 b를 만족하는가?
    시간 순서대로 살펴보면
    [False, False, False, False, ...  True, True, .....] 형태가 나오기 때문에
    최초의 True를 만족하는 시간을 찾으면 된다. => 이분탐색 (lower_bound)

    Condition는 조건을 만족하는지 여부인데 조건을 만족하는 여부는 다음과 같다.
    
    cnt = (time - t[i]) // (2 * t[i])+ 1 
    금 또는 은을 해당 도시의 트럭으로 조달하기 위해서 특정 시간이 주어졌을 때 몇번 옮겼는지 구해주는 식이다.
    걸리는 시간은 마지막 시간 뺴고는 왕복해서 수행하므로 위와 같은 식이 나온다.
    이 식을 기준으로 truck이 실어서 갈 수 있는 양을 곱하면 총량이 된다. 
    매 도시마다 트럭의 담을 수 있는 총량을 더했을 때
    도시별 금을 옮길 최대 수량의 합 >= a, 도시별 은을 옮길 최대 수량의 합 >= b, truck의 옮긴 총량 >= a + b가 만족하면 된다.
    마지막 truck이 옮긴 총량을 구해주는 이유는 금은이 개별적으로 최대 수량이 a,b 보다 크더라도 분배했을 때 총량 또한 만족해야하기 때문이다.

    조심해야 할 점은 주어진 시간에 트럭이 옮긴 금, 은 양은 도시가 보유한 양을 초과할수 없다는 점이다. 이에 유의한다.

    Condition 짜는게 굉장히 까다로운 문제

"""
def solution(a, b, g, s, w, t):
    def lower_bound(lo, hi, condition):
        while lo < hi:
            mid = (lo + hi) // 2
            if condition(mid):
                hi = mid
            else:
                lo = mid + 1
        return hi

    def condition(time):
        G = 0
        S = 0
        W = 0
        for i in range(len(t)):
            if time < t[i]:
                continue
            cnt = (time - t[i]) // (2 * t[i])+ 1
            truck_total_weight = cnt * w[i]
            G += min(truck_total_weight, g[i])  # 트럭이 옮길 수 있는 양은 도시가 보유한 량을 초과 할 수 없음
            S += min(truck_total_weight, s[i])
            W += min(truck_total_weight, g[i] + s[i])
        if G >= a and S >= b and W >= a + b:
            return True
        return False

    return lower_bound(0, 10 ** (9 + 5 + 2), condition)