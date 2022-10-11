for tnt in range(1, int(input())+1):

    n, l = map(int, input().split())

    mat_width = [list(input().split()) for _ in range(n)]
    mat_height = list(map(list, zip(*mat_width)))

    arr = []

    for i in range(n):
        w = ''.join(mat_width[i]).replace('0', ' ').split()
        h = ''.join(mat_height[i]).replace('0', ' ').split()

        arr.extend(w)
        arr.extend(h)

    result = 0

    for i in arr:
        if len(i) == l:
            result += 1

    print(f'#{tnt} {result}')
