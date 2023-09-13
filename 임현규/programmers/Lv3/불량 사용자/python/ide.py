from itertools import permutations


def solution(user_id, banned_id):

    def check(perm):
        for i in range(len(perm)):
            if len(perm[i]) != len(banned_id[i]):
                return False
            for j in range(len(perm[i])):
                if banned_id[i][j] != '*' and perm[i][j] != banned_id[i][j]:
                    return False
        return True

    result = set()
    for perm in permutations(user_id, len(banned_id)):
        if (check(perm)):
            result.add(tuple(sorted(perm)))
    return len(result)