dp = [1000001] * 1000001
dp[1] = 0
dp[2] = 1
dp[3] = 1
for i in range(4, 1000001):

    # i에서 1로 가는 3가지 방법에서 최소값을 담아줌
    # dp로 이전값들을 저장하고 활용해서 해결
    if i % 3 == 0:
        dp[i] = min(dp[i], dp[i // 3] + 1)
    if i % 2 == 0:
        dp[i] = min(dp[i], dp[i // 2] + 1)

    dp[i] = min(dp[i], dp[i - 1] + 1)

print(dp[int(input())])

