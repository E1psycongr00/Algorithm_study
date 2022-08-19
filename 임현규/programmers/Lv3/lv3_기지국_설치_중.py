import math


def solution(n, stations, w):
    intervals = [[0 - w, 0]] + list(map(lambda station: station_to_interval(station, w), stations)) + [
        [n + 1, n + 1 + w]]

    merged_intervals = merge(intervals)
    numbers = []
    for i in range(len(merged_intervals) - 1):
        val = find_undefined_area(merged_intervals[i], merged_intervals[i + 1])
        numbers.append(val)
    return sum(map(lambda num: math.ceil(num / (2*w+1)), numbers))
    


def station_to_interval(station, w):
    return [station - w, station + w]


def is_merged(left, right):
    return left[1] >= right[0] - 1


def merge(intervals):
    stack = []
    for interval in intervals:
        if stack and is_merged(stack[-1], interval):
            left = stack.pop()
            stack.append([left[0], interval[1]])
        else:
            stack.append(interval)
    return stack


def find_undefined_area(left, right):
    return right[0] - left[1] - 1