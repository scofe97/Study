dp = [0] * 1000001
dp[1] = 1
dp[2] = 2

for i in range(3, 1000001):
    # 길이 3부터는, 1로 시작하는 경우와, 00으로 시작하는 경우로 나뉜다.
    dp[i] = (dp[i-1] + dp[i-2]) % 15746

num = int(input())
print(dp[num])