""" 문제 자체는 쉬웠지만 간격 세부 설정이 생각하기 어려웠다.
    문제의 풀이는 다음과 같다.
    1. lines를 문자열 파싱을 통해 가져온다.
    2. 간격 별로 비교한다. O(N^2)
    간격 별로 비교 할 때 1초 간격이라는 조건이 있다. 이 부분 생각하는 것이
    까다로운데 다음과 같다.
    interval 1: [1,  2,  3]
    interval 2:     [2,  3,  4]
    check     : [1,  2]
    이렇게 interval 1의 가장 오른쪽 숫자는 interval 2의 숫자에 -1초
    interval_1.end > interval_2.start - 1 을 만족하면 된다.
    만약 end가 -1보다 작으면 어떻게 되는 지 살펴보자

    interval 1: [1, 2]
    interval 2:          [4,  5]
    check     :       [3, 4]

    interval 1과 interval2는 1초 간격에서 만날 수 없음을 알 수 있다.
    주어진 문제는 [ms] 이므로 -1000 을 해주면 된다.
"""
from dataclasses import dataclass
import re


@dataclass
class Interval:
    start_time: int
    end_time: int


def parse_log(log):
    pattern = r"(?P<hh>\d+):(?P<mm>\d+):(?P<ss>\d+\.\d*)\s(?P<duration>\d+\.*[0-9]*)s"
    match_result = re.search(pattern, log)
    info = match_result.groupdict()
    
    milis = (int(info["hh"]) * 3600 + int(info["mm"]) * 60) * 1000 + int(
        float(info["ss"]) * 1000
    )
    duration = int(float(info["duration"]) * 1000)
    return Interval(milis - duration + 1, milis)


def make_interval(lines):
    intervals = []
    for line in lines:
        intervals.append(parse_log(line))
    return intervals


def solution(lines):
    intervals = make_interval(lines)
    n = len(lines)
    result = 0
    for i in range(n):
        cnt = 0
        for j in range(i, n):
            if intervals[i].end_time > intervals[j].start_time - 1000:
                cnt += 1
        result = max(cnt, result)
    return result

print(
    solution(
        [
            "2016-09-15 20:59:57.421 0.351s",
            "2016-09-15 20:59:58.233 1.181s",
            "2016-09-15 20:59:58.299 0.8s",
            "2016-09-15 20:59:58.688 1.041s",
            "2016-09-15 20:59:59.591 1.412s",
            "2016-09-15 21:00:00.464 1.466s",
            "2016-09-15 21:00:00.741 1.581s",
            "2016-09-15 21:00:00.748 2.31s",
            "2016-09-15 21:00:00.966 0.381s",
            "2016-09-15 21:00:02.066 2.62s",
        ]
    )
)
