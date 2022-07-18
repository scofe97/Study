result = []

for tnt in range(1, int(input()) + 1):
    arr = list(map(int,input().split()))

    for i in range(5):
        if arr[i] < 40:
            arr[i] = 40

    result.append(f'#{tnt} {sum(arr) // 5}')

for i in result:
    print(i)
