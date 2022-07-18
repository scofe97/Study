size = 1000
dp = [0] * (size + 1)
dp[1] = 1
dp[2] = 2
dp[3] = 3

# 패턴은 2개로 시작하는가, 혹은 3칸으로 시작하걸로 나누어서 계산했다
# 2개로 시작하면 경우가 2개이므로 *2를 해줌
for i in range(4, size + 1):
    dp[i] = (dp[i-2] * 2 + dp[i-3]) % 10007

print(dp[int(input())])

