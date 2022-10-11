for tnt in range(1, int(input())+1):
    n = int(input())
    arr = list(map(int, input().split()))
    result = 0

    dp = [arr[0]] * n

    for i in range(1, n):
        if dp[i-1] + arr[i] > arr[i]:
            dp[i] = dp[i-1] + arr[i]
        else:
            dp[i] = arr[i]

    print(f'#{tnt} {max(dp)}')