def solution(order):
    order = order[::-1]
    main = list(range(1, len(order) + 1))[::-1]
    sub = []

    cnt = 0
    while order and (main or sub):
        val = order.pop()
        if sub and sub[-1] == val:
            sub.pop()
            cnt += 1
        else:
            while main and main[-1] != val:
                sub.append(main.pop())
            if main and main[-1] == val:
                main.pop()
                cnt += 1
        
            else:
                break
    return cnt

print(solution([4, 3, 1, 2, 5]	))