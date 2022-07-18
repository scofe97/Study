dp = [[0,0] for _ in range(41)]
dp[0][0] = 1
dp[1][1] = 1

for i in range(2, 41):

    # 피보나치 n은 n-1, n-2를 더해서 만들어지고
    # n-1, n-2에서 나오는 0과 1만큼을 더하면 된다.
    dp[i][0] = dp[i-2][0] + dp[i-1][0]
    dp[i][1] = dp[i-2][1] + dp[i-1][1]


for tnt in range(int(input())):
    num = int(input())

    print(dp[num][0], dp[num][1])