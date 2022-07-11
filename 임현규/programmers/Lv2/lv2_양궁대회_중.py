def checkScore(appeach, lion):
    diff = 0  # diff < 0 appeach win, diff > 0 lion win
    for i in range(11):
        if appeach[i] == lion[i] == 0:
            diff += 0
        elif appeach[i] >= lion[i]:
            diff -= (10 - i)
        else:
            diff += (10 - i)
    return diff


def findLowIndex(before, after):
    for i in range(10, -1, -1):
        if after[i] > before[i]:
            return True
        elif after[i] < before[i]:
            return False
        else:
            continue
    return False


def getMaxScoreAndArray(n, appeach):
    max_value = 0
    lion = [0] * 11
    answer = []
    
    def backTracking(n, appeach, step):
        nonlocal max_value, answer
        if n > 0 and step >= 11:
            return
        
        if n == 0:
            value = checkScore(appeach, lion)
            if max_value < value:
                max_value = value
                answer = lion.copy()
            elif max_value == value and max_value != 0:
                if findLowIndex(answer, lion):
                    answer = lion.copy()
            return
            
        for i in range(0, n+1):
            lion[step] = i
            backTracking(n-i, appeach, step+1)
            lion[step] = 0
    backTracking(n, appeach, 0)
    
    return answer if max_value > 0 else [-1]
                
    
def solution(n, appeach:list):
    
    answer = getMaxScoreAndArray(n, appeach)
    return answer