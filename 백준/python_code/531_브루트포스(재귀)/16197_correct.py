import sys
from collections import deque
input = sys.stdin.readline

dy = [-1, 0, 1, 0]
dx = [0, -1, 0, 1]


def BFS():
    while coin:
        y2, x2, y1, x1, cnt = coin.popleft()

        if cnt >= 10:
            return -1

        for i in range(4):
            ny2 = y2 + dy[i]
            nx2 = x2 + dx[i]
            ny1 = y1 + dy[i]
            nx1 = x1 + dx[i]

            if (0 <= ny2 < n) and (0 <= nx2 < m) and (0 <= ny1 < n) and (0 <= nx1 < m):
                if board[ny2][nx2] == "#":
                    ny2, nx2 = y2, x2
                if board[ny1][nx1] == "#":
                    ny1, nx1 = y1, x1
                coin.append([ny2, nx2, ny1, nx1, cnt+1])

            elif (0 <= ny2 < n) and (0 <= nx2 < m):
                return cnt + 1

            elif (0 <= ny1 < n) and (0 <= nx1 < m):
                return cnt + 1

            else:
                continue

    return -1


n, m = map(int, input().split())

coin = deque()

board = []
temp = []
for i in range(n):
    board.append(list(input().strip()))
    for j in range(m):
        if board[i][j] == "o":
            temp.append((i, j))

coin.append((temp[0][0], temp[0][1], temp[1][0], temp[1][1], 0))

print(BFS())
