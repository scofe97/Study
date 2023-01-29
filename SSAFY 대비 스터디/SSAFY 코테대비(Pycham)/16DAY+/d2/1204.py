for _ in range(int(input())):
    tnt = int(input())

    arr_list = list(map(int,input().split()))
    arr_set = set(arr_list)

    cnt = 0
    result = 0

    for i in arr_set:
        if cnt < arr_list.count(i):
            cnt = arr_list.count(i)
            result = i

    print(f'#{tnt} {result}')


    # 03:40