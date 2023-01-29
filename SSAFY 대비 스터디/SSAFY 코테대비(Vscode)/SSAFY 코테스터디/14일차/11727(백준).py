size = 1000
dp = [0] * (size + 1)
dp[1] = 1
dp[2] = 3

for i in range(3, size + 1):
    dp[i] = (dp[i-2] + dp[i-2] + dp[i-1]) % 10007

print(dp[int(input())])