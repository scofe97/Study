for tnt in range(int(input())):
    # n 몇명, m 붕어빵 k개당 만드는시간, k 몇개 만드냐
    n, m, k = map(int, input().split())
    arr = list(map(int, input().split()))
    arr.sort()
    result = "Possible"

    for i in range(n):
        check = (arr[i] // m) * k - (i+1)
        if check < 0:
            result = "Impossible"
            break

    print(f'#{tnt+1} {result}')