def prime(minNum, maxNum):

    size = int(maxNum ** 0.5) + 1
    minNum = 2 if minNum < 2 else minNum
    dp = [1] * (maxNum+1)

    for i in range(2, size):
        if dp[i]:
            for j in range(i+i, maxNum+1, i):
                dp[j] = 0

    return [i for i in range(minNum, maxNum+1) if dp[i]]

for tnt in range(1, int(input())+1):
    d, num_min, num_max = map(int, input().split())
    d = str(d)
    p = prime(num_min, num_max)
    r = 0

    for i in p:
        if str(i).count(d):
                r += 1

    print(f'#{tnt} {r}')