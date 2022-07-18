for tnt in range(1, int(input())+1):
    n1, n2 = map(int, input().split())
    dp = [0,1,3]

    for i in range(3,10000):
        dp.append(dp[-1] + i)

    for i in range(len(dp)-1):
        if n1 > dp[i] and n1 <= dp[i+1] :
            point = n1 - dp[i]
            x1 = point
            y1 = i+2-point
            break

    for i in range(len(dp)-1):
        if n2 > dp[i] and n2 <= dp[i+1]:
            point = n2 - dp[i]
            x2 = point
            y2 = i+2-point
            break

    result_x = x1+x2
    result_y = y1+y2

    result = dp[result_x+result_y-2] + result_x
    print(f'#{tnt} {result}')


