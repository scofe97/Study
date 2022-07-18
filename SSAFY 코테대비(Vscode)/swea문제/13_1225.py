for _ in range(10):
    tnt = int(input())
    arr = list(map(int, input().split()))
    cnt = 1

    while True:

        if arr[-1] <= 0:
            arr[-1] = 0
            break

        temt = arr[0]
        del[arr[0]]
        arr.append(temt-cnt)

        if cnt >= 5:
            cnt = 1
        else:
            cnt += 1


    print(f'#{tnt}', end=' ')
    for i in range(len(arr)):
        print(f'{arr[i]}', end=' ')
    print()
