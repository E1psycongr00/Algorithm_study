def solution(sticker):
    val1 = max_pick_sticker(sticker=sticker, is_pick_first=True)
    val2 = max_pick_sticker(sticker=sticker, is_pick_first=False)
    return max(val1, val2)


def max_pick_sticker(sticker, is_pick_first):
    if len(sticker) == 1:
        return sticker[0]
    
    dp = [0 for _ in range(len(sticker))]
    dp[0] = sticker[0] if is_pick_first else 0
    dp[1] = sticker[0] if is_pick_first else sticker[1]
    
    n = len(sticker) -1 if is_pick_first else len(sticker)
    
    for i in range(2, n):
        dp[i] = max(dp[i-1], dp[i-2] + sticker[i])
    
    return max(dp)