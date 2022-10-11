def move(cnt, y, x, s):
    global result

    if cnt == 6:
        result.add(s)
        return

    if 0 <= x < 5 and 0 <= y < 5:
        s += arr[y][x]

        dy = [0,0,1,-1]
        dx = [-1,1,0,0]

        for i in range(4):
            y2 = y + dy[i]
            x2 = x + dx[i]
            move(cnt+1, y2, x2, s)



arr = [list(input().split()) for _ in range(5)]
result = set()

for n in range(5):
    for m in range(5):
        move(0, n, m, '')

print(len(result))



