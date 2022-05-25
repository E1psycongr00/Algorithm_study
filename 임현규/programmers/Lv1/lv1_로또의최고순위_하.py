def solution(lottos, win_nums):
    zeros = lottos.count(0)
    same = len(set(lottos) & set(win_nums))
    rank_table = [6,6,5,4,3,2,1]
    return [rank_table[same + zeros], rank_table[same]]