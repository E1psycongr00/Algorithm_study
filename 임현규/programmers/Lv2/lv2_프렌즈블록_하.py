from collections import defaultdict
from typing import List, Set


def solution(row_size: int, col_size: int, board: List[str]) -> int:
    """보드를 가져와 삭제할 수 있는 사각형 수를 반환

    Parameters
    ----------
    row_size : int
        보드의 행 수.
    col_size : int
        가로 길이
    board : List[str]
        ["TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"]

    Returns
    -------
        삭제된 사각형의 수

    """
    game_board = make_game_board(row_size, col_size, board)
    cnt = 0
    while True:
        delete_pos = find_delete_position(game_board)
        if not delete_pos:
            break
        for row in delete_pos.keys():
            for col in delete_pos[row]:
                game_board[row][col] = "0"
                cnt += 1
        delete_pos.clear()
        gravity(game_board)
    return cnt


def make_game_board(row_size: int, col_size: int, board: List[str]) -> List[List[str]]:
    game_board: List[List[str]] = []
    for i in range(len(board)):
        game_board.append(list(board[i]))
    return game_board


def find_delete_position(board: List[List[str]]) -> defaultdict[int, Set[int]]:
    delete_pos: defaultdict[int, Set[int]] = defaultdict(set)
    for i in range(len(board) - 1):
        for j in range(len(board[0]) - 1):
            if is_same_square(i, j, board) and board[i][j] != "0":
                delete_pos[i].update([j, j + 1])
                delete_pos[i + 1].update([j, j + 1])
    return delete_pos


def is_same_square(row: int, col: int, board: List[List[str]]) -> bool:
    return (
        board[row][col]
        == board[row + 1][col]
        == board[row][col + 1]
        == board[row + 1][col + 1]
    )


def gravity(board: List[List[str]]) -> None:
    rows = len(board)
    cols = len(board[0])
    for col in range(cols):
        for _ in range(rows):
            for row in range(rows - 1, 0, -1):
                if board[row][col] == "0":
                    board[row][col], board[row - 1][col] = (
                        board[row - 1][col],
                        board[row][col],
                    )


print(solution(4, 5, ["CCBDE", "AAADE", "AAABF", "CCBBF"]))
