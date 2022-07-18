def check(type):
    global arr

    if type == 1:
        for i in range(n,size):
            if arr[i][m] == 2:
                arr[n][m] = 3
                arr[i][m] = 3
                return 1
            
            if arr[i][m] == 3:
                arr[n][m] = 3
                return 0

    elif type == 2:
        for i in reversed(range(0,n)):
            if arr[i][m] == 1:
                arr[n][m] = 3
                arr[i][m] = 3
                return 1

            if arr[i][m] == 3:
                arr[n][m] = 3
                return 0

    return 0           

for tnt in range(10):

    size = int(input())
    arr = [list(map(int,input().split())) for _ in range(size)]
    result = 0
    for n in range(size):
        for m in range(size):
            result += check(arr[n][m])

    print(f'#{tnt+1} {result}')
