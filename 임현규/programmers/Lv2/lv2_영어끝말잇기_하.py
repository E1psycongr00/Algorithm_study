"""
Rule대로 구현하고 결과에 필요한 값을 INDEX를 가공해 리턴하는 풀이
"""
from typing import List, Set


def solution(n: int, words: List[str]):
    """현재 단어의 길이가 1 이상이고 현재 단어의 시작 글자가 이전 단어의 마지막 글자와 같지 않고 현재 단어가 사용된 단어 집합에 없으면
     현재 단어를 사용된 단어 집합에 추가합니다.
    그렇지 않으면 현재 단어와 차례 번호를 반환

    Parameters
    ----------
    n : int
        게임을 하는 사람들의 수
    words : List[str]
        목록[str] = ["탱크", "차기", "알다", "바퀴", "땅", "꿈", "어머니", "로봇", "탱크"]

    Returns
    -------
        반환 값은 두 개의 정수 목록 [사람 번호, X번 째 말한 단어]

    """
    used: set[str] = set()
    words.insert(0, "abababa" + words[0][0])
    for i, word in enumerate(words[1:], 1):
        before_word = words[i - 1]
        current_word = word
        if is_collect_word(before_word, current_word, used):
            used.add(current_word)
        else:
            print(i, current_word)
            turn = (i - 1) // n + 1
            man = (i - 1) % n + 1
            return [man, turn]
    return [0, 0]


def is_collect_word(before_word: str, current_word: str, used: Set[str]) -> bool:
    current_word_size: int = len(current_word)
    if current_word_size == 1:
        return False
    if before_word[-1] != current_word[0]:
        return False
    if current_word in used:
        return False
    return True
