for tnt in range(1, int(input())+1):
    cnt = int(input())

    arr = list(map(int, input().split()))
    result = 0

    while arr:
        max_idx = arr.index(max(arr))
        result += max(arr)*len(arr[:max_idx]) - sum(arr[:max_idx])

        if max_idx + 1 == cnt:
            break
        else:
            arr = arr[max_idx+1:]

    print(f'#{tnt} {result}')