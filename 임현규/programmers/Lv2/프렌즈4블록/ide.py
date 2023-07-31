def is_square_same(i, j, board):
    return board[i][j] == board[i][j + 1] == board[i + 1][j] == board[i + 1][j + 1]


def delete_block(m, n, new_board):
    delete_set = set()
    for i in range(n - 1):
        for j in range(m - 1):
            if new_board[i][j] != "@" and is_square_same(i, j, new_board):
                delete_set.update([(i, j), (i, j + 1), (i + 1, j), (i + 1, j + 1)])
    for y, x in delete_set:
        new_board[y][x] = "@"
    for i, row in enumerate(new_board):
        empty = ["@"] * row.count("@")
        new_board[i] = [r for r in row if r != "@"] + empty
    return len(delete_set)


def solution(m, n, board):
    new_board = list(map(list, zip(*board[::-1])))
    cnt = 0
    while True:
        count = delete_block(m, n, new_board)
        if count == 0:
            break
        cnt += count
    return cnt
