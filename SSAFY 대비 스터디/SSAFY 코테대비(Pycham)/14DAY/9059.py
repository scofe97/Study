dp = [0] * 11
dp[1] = 1
dp[2] = 2
dp[3] = 4

for i in range(4, 11):
    # 길이가 i인 경우의 패턴은 1~3 의 숫자 중 하나로 시작한다.
    # 그러므로 1~3을 뺀 숫자의 합을 더하면된다.
    dp[i] = dp[i-1] + dp[i-2] + dp[i-3]

for i in range(int(input())):
    print(dp[int(input())])