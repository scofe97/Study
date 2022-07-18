for tnt in range(1, int(input())+1):
    size = int(input())
    arr = [[] for _ in range(size+1)]
    arr[1].append(1)

    for i in range(2, size+1):
        arr[i].append(arr[i-1][0])
        for j in range(1, i-1):
            arr[i].append(arr[i-1][j-1] + arr[i-1][j])
        arr[i].append(arr[i - 1][-1])


    print(f'#{tnt}')
    for i in range(1, size+1):
        print(*arr[i])

# 09:17