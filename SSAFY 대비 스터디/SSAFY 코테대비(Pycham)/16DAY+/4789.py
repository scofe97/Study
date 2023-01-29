for tnt in range(1, int(input())+1):
    arr = list(input())

    c = 0
    r = 0

    for i in range(len(arr)):
        idx = int(arr[i])

        if idx and c >= i:
            c += idx

        elif idx:
            r += i-c
            c = i+idx

    print(f'#{tnt} {r}')

