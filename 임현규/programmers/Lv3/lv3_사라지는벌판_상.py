import sys
from copy import deepcopy

sys.setrecursionlimit(10 ** 6)

WIN = 1
LOSE = -1
NOT_UPDATED = 0

UP, DOWN, LEFT, RIGHT = (-1, 0), (1, 0), (0, -1), (0, 1)


def get_status(board, aloc, bloc, player):
    if player == "A":
        if board[aloc[0]][aloc[1]] == 0:
            return LOSE
    if player == "B":
        if board[bloc[0]][bloc[1]] == 0:
            return LOSE
    return NOT_UPDATED


def can_move(board, y, x):
    rows, cols = len(board), len(board[0])
    return 0 <= y < rows and 0 <= x < cols and board[y][x] == 1


def play(board, aloc, bloc, player, step):
    # print(f"{board}, aloc: {aloc}, bloc: {bloc}, player: {player}, step: {step}")
    status = get_status(board, aloc, bloc, player)
    if status != NOT_UPDATED:
        return status, step
    
    enemy_win = []
    enemy_lose = []
    loc = aloc if player == "A" else bloc
    next_player = "B" if player == "A" else "A"
    
    for dy, dx in [UP, DOWN, LEFT, RIGHT]:
        next_y = loc[0] + dy
        next_x = loc[1] + dx
        if can_move(board, next_y, next_x):
            next_board = deepcopy(board)
            next_board[loc[0]][loc[1]] = 0
            next_aloc, next_bloc = None, None
            if player == "A":
                next_aloc = (next_y, next_x)
                next_bloc = bloc
            else:
                next_aloc = aloc
                next_bloc = (next_y, next_x)
            
            status, val = play(next_board, next_aloc, next_bloc, next_player, step + 1)
            
            if status == WIN:
                enemy_win.append(val)
            else:
                enemy_lose.append(val)
    if enemy_lose:
        return WIN, min(enemy_lose)
    elif enemy_win:
        return LOSE, max(enemy_win)
    else:
        return LOSE, step


def solution(board, aloc, bloc):
    _, answer = play(board, aloc, bloc, "A", 0)
    return answer


board = [[1, 1, 1], [1, 1, 1], [1, 1, 1]]
aloc = [1, 0]
bloc = [1, 2]

print(solution(board, aloc, bloc))
