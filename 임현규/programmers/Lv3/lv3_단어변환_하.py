import sys

result = sys.maxsize
seen = set()


def checkSpelling(s1, s2):
    cnt = 0
    for i in range(len(s1)):
        if s1[i] != s2[i]:
            cnt += 1
    return cnt == 1


def backTracking(picked, target, words):
    global result, seen
    if picked[-1] == target:
        result = min(len(picked)-1, result)
        return
    
    for i in range(len(words)):
        if checkSpelling(words[i], picked[-1]) and i not in seen:
            seen.add(i)
            picked.append(words[i])
            backTracking(picked, target, words)
            seen.remove(i)
            picked.pop()
    


def solution(begin, target, words):
    backTracking([begin], target, words)
    return result if result != sys.maxsize else 0

print(solution("hot", "dog", ["hit", "dog"]))