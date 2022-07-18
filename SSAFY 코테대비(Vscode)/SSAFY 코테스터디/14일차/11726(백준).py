size = 1000
dp = [0] * (size + 1)
dp[1] = 1
dp[2] = 2
dp[3] = 3


for i in range(4, size + 1):
    dp[i] = (dp[i-2] * 2 + dp[i-3]) % 10007

print(dp[int(input())])

