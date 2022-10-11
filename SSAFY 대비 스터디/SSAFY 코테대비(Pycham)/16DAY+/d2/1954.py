dx = [1,0,-1,0]
dy = [0,1,0,-1]

def dfs(cnt, x, y, d):

    if cnt == n*n:
        return

    while True:
        if visited[y][x]:
            break

        visited[y][x] = 1
        cnt += 1
        arr[y][x] = cnt

        x2 = x + dx[d]
        y2 = y + dy[d]
        if 0 <= x2 < n and 0 <= y2 < n and not visited[y2][x2] :
            x = x2
            y = y2
        else:
            break

    d_cnt = (d+1) % 4
    dfs(cnt, x+dx[d_cnt], y+dy[d_cnt], d_cnt)

for tnt in range(1, int(input())+1):

    n = int(input())
    arr = [[0] * n for _ in range(n)]
    visited = [[0] * n for _ in range(n)]

    dfs(0, 0, 0, 0)

    print(f'#{tnt}')

    for i in arr:
        print(* i)




# 26:52