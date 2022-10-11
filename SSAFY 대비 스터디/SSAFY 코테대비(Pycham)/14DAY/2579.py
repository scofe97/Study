arr = [0]
size = int(input())

for _ in range(size):
    arr.append(int(input()))

if size > 1:
    dp = [0] * (size+1)
    dp[1] = arr[1]
    dp[2] = arr[2] + arr[1]

    # 1칸을 연속으로 3칸이상 오르면 안된다
    # n번째 칸에 있다면 최대값이 밑의 두 값중 하나이다.
        # dp[n-3] arr(n-1) -> 3칸이전에서 2칸 움직이고 1칸 움직임
        # dp[n-2]  -> 그냥 2칸을 움직인 경우

    for i in range(3, size+1):
        dp[i] = max(arr[i-1] +  dp[i-3], dp[i-2]) + arr[i]

    print(dp[size])

else:
    print(arr[1])