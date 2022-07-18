for tnt in range(1, int(input())+1):
    arr = list(map(int, input().split()))

    result = 0

    for i in arr:
        if i % 2:
            result += i

    print(f'#{tnt} {result}')