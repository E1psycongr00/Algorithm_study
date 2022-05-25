def solution(numbers, hand):
    def getDistance(num1, num2):
        y1, x1 = divmod(num1-1, 3)
        y2, x2 = divmod(num2-1, 3)
        return abs(y1 - y2) + abs(x1 - x2)
    
    result = []
    left = 10
    right = 12
    
    for num in numbers:
        if num == 0:
            num = 11
        if num in [1, 4, 7]:
            left = num
            result.append('L')
        elif num in [3, 6, 9]:
            right = num
            result.append('R')
        else: # 2, 5, 8, 0
            dLeft = getDistance(num, left)
            dRight = getDistance(num, right)
            if dLeft < dRight:
                left = num
                result.append('L')
            elif dLeft > dRight:
                right = num
                result.append('R')
            else: # 같은 경우
                if hand == 'left':
                    left = num
                    result.append('L')
                else:
                    right = num
                    result.append('R')
    return ''.join(result)