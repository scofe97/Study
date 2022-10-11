for tnt in range(1, int(input())+1):
    num = int(input())

    arr = [50000, 10000, 5000, 1000, 500, 100, 50, 10]
    result = []

    for i in arr:
        p, r = divmod(num, i)
        result.append(p)
        num = r


    print(f'#{tnt}')
    for i in result:
        print(f'{i}', end=' ')
    print()