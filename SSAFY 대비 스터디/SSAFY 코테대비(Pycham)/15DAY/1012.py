def check(y, x):
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    # 상,하,좌,우 확인
    for i in range(4):
        ny = y + dy[i]
        nx = x + dx[i]

        if (0 <= ny < n) and (0 <= nx < m):
            if arr[ny][nx] == 1:
                arr[ny][nx] = -1
                check(ny, nx)


for tnt in range(int(input())):
    n, m, k = map(int, input().split())
    arr = [[0] * m for _ in range(n)]
    result = 0

    for i in range(k):
        y, x = map(int, input().split())
        arr[y][x] = 1

    for y in range(n):
        for x in range(m):
            if arr[y][x] == 1:
                check(y, x)
                result += 1

    print(result)