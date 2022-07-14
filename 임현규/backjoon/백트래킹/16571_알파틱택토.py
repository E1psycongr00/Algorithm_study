import sys
from collections import Counter
from copy import deepcopy
from itertools import chain
import json

sys.setrecursionlimit(10 ** 6)
cache = {}

WIN = 1
LOSE = -1
DRAW = 0
NOT_UPDATED = 987654321  # 둘 돌이 없어 업데이트 되지 않음


def get_current_board():
    board = []
    for i in range(3):
        board.append(list(map(int, input().split())))
    return board


def check_player_turn(board):
    counter = Counter(chain(*board))
    if counter[1] == counter[2]:
        return 1
    else:
        return 2


def is_finished(board):
    # 경기 승패 조건
    for i in range(3):
        # 가로
        if board[i][0] == board[i][1] == board[i][2] and board[i][0] != 0:
            return True
        # 세로
        if board[0][i] == board[1][i] == board[2][i] and board[0][i] != 0:
            return True
        # 대각선
        if board[0][0] == board[1][1] == board[2][2] and board[1][1] != 0:
            return True
        if board[0][2] == board[1][1] == board[2][0] and board[2][0] != 0:
            return True
    return False


def action(board, i, j, player, next_match_result):
    next_player = 1 if player == 2 else 2
    if board[i][j] == 0:
        next_board = deepcopy(board)
        next_board[i][j] = player
        next_match_result = min(next_match_result, play(next_board, next_player))
    
    return next_match_result


def get_match_result(next_match_result):
    if next_match_result == WIN:
        return LOSE
    if next_match_result == LOSE:
        return WIN
    else:
        return DRAW


def play(board, player):
    # 이미 경기 결과가 있다면 경기 결과 리턴
    str_board = json.dumps(board)
    if str_board in cache:
        return cache[str_board]
    
    # 경기 체크 결과 play 이전에 상대가 3개 돌을 연속적으로 두어 결과가 결정 났다면 패배 리턴
    if is_finished(board):
        return LOSE
    
    # 플레이 할 목록을 살펴 본 다음 액션을 취한다 그리고 상대방 플레이 턴으로 넘긴다
    # 1. 상대방이 져야 내가 이길 수 있다
    # 2. 차선책은 상대방과 비겨야한다
    next_match_result = NOT_UPDATED
    
    for i in range(3):
        for j in range(3):
            next_match_result = action(board, i, j, player, next_match_result)
    
    # 상대방 결과를 바탕으로 현재 승부 상태를 리턴 할 방안을 찾는다
    match_result = get_match_result(next_match_result)
    
    # 경기 결과를 캐시에 저장한다
    cache[str_board] = match_result
    return match_result


def solution():
    board = get_current_board()
    player = check_player_turn(board)
    
    match_result = play(board, player)
    
    if match_result == 1:
        print("W")
    elif match_result == -1:
        print("L")
    else:
        print("D")


solution()
