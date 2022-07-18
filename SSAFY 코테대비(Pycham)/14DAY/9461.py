dp = [1] * 101
dp[4] = 2
dp[5] = 2

for i in range(6, 101):
    # 설명보다는 그냥 그림보다 보면 이 패턴으로 생성된다.
    dp[i] = dp[i-1] + dp[i-5]

for _ in range(int(input())):
    num = int(input())

    print(dp[num])

