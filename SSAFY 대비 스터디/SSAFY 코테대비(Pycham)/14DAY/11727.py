size = 1000
dp = [0] * (size + 1)
dp[1] = 1
dp[2] = 3

for i in range(3, size + 1):
    # 세로 1인 경우, 가로 2인 경우, 2x2 사각형으로 시작하는 패턴으로 나뉨
    dp[i] = (dp[i-2] + dp[i-2] + dp[i-1]) % 10007

print(dp[int(input())])