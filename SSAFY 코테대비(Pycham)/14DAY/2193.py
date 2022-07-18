dp = [[0,0] for _ in range(91)]
dp[1][0] = 1
dp[1][1] = 1

result = 0

for i in range(2, 91):
    # 0으로 시작해도 되는경우, 뒤의 숫자가 1이든 0이든 상관없다
    dp[i][0] = dp[i-1][1] + dp[i-1][0]
    # 1로 시작하는 경우, 뒤의 숫자가 0으로 시작해야한다.
    dp[i][1] = dp[i-1][0]

print(dp[int(input())][1])