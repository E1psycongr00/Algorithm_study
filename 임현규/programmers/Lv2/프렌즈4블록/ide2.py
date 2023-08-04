def delete_block(m, n, board):
    delete_points = set()
    for i in range(m - 1):
        for j in range(n - 1):
            if board[i][j] != "@" and board[i][j] == board[i][j + 1] == board[i + 1][j] == board[i + 1][j + 1]:
                delete_points.update([(i, j), (i, j + 1), (i + 1, j), (i + 1, j + 1)])

    for y, x in delete_points:
        board[y][x] = "@"

    for j in range(n):
        write_index = m - 1
        for i in range(m - 1, -1, -1):
            if board[i][j] != "@":
                board[write_index][j] = board[i][j]
                write_index -= 1
        for i in range(write_index, -1, -1):
            board[i][j] = "@"
    return len(delete_points)


def solution(m, n, board):
    new_board = [list(b) for b in board]
    cnt = 0
    while True:
        count = delete_block(m, n, new_board)
        if count == 0:
            break
        cnt += count
    return cnt


print(solution(4, 5, ["CCBDE", "AAADE", "AAABF", "CCBBF"]))
