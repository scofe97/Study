for tnt in range(1, int(input())+1):
    size = int(input())
    arr = list(map(int,input().split()))
    result = 0

    for i in range(1, size-1):
        temp = arr[i-1:i+2]

        temp_max = max(temp)
        temp_min = min(temp)

        if arr[i] != temp_min and arr[i] != temp_max:
            result += 1

    print(f'#{tnt} {result}')