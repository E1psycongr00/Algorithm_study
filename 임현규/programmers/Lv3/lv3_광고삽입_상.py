""" constant range 1d array dp => prefix sum 으로 바꾸는 문제
그 이후 슬라이딩 윈도우를 적용하여 시간을 구한다.
다만 유의점은 log에서 조회수를 뽑아 낼 때 [이상, 이하] 가 아니라 [이상, 미만]이다.
그 이유는 종료 시간엔 유저가 빠져나가는 순간이 됨으로 1에서 0이 되기 때문이다.

"""
from copy import copy


def solution(play_time, adv_time, logs):
    dp = [0] * 360000
    for log in logs:
        start_time, end_time = log.split("-")
        update_constant_range_1d_array(dp, to_seconds(start_time), to_seconds(end_time))

    arr = to_prefix_sum(dp)
    max_time = find_max_time(arr, to_seconds(adv_time))
    return to_time(max_time)

def find_max_time(arr, width):
    """
    주어진 기간 동안의 최대 조회수를 찾습니다.
    
    Args:
      arr: 뷰의 배열
      width: 창의 너비
    
    Returns:
      최대 시간의 인덱스
    """
    most_view = 0                           # 5
    max_time = 0
    right = width - 1
    if most_view < arr[right] - 0:
        most_view = arr[right]
        max_time = i - width + 1                          
    for i in range(width, len(arr)):
        if most_view < arr[i] - arr[i - width]:
            most_view = arr[i] - arr[i - width]
            max_time = i - width + 1
    return max_time


def update_constant_range_1d_array(dp, start, end):
    """ 이상, 미만
    """
    dp[start] += 1
    dp[end] -= 1


def to_prefix_sum(constant_array_1d_array_dp):
    arr = copy(constant_array_1d_array_dp)
    for i in range(1, len(arr)):
        arr[i] += arr[i - 1]
    
    for i in range(1, len(arr)):
        arr[i] += arr[i - 1]
    return arr


def to_seconds(time):
    HH, MM, SS = map(int, time.split(":"))
    return HH * 3600 + MM * 60 + SS


def to_time(seconds):
    HH = str(seconds // 3600).zfill(2)
    MM = str((seconds % 3600) // 60).zfill(2)
    SS = str(seconds % 60).zfill(2)
    return f"{HH}:{MM}:{SS}"



print(
    solution(
        "02:03:55",
        "00:14:15",
        [
            "01:20:15-01:45:14",
            "00:40:31-01:00:00",
            "00:25:50-00:48:29",
            "01:30:59-01:53:29",
            "01:37:44-02:02:30",
        ],
    )
)
