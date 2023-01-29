for tnt in range(1, int(input()) + 1):

    # * 초기화 부분
    n, m = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(n)]

    # * dp는 가방의 부피의 길이만큼 생성해줌
    dp = [0] * (m + 1)

    # * i는 몇번째 물건, w는 무게
    for i in range(n):
        for w in range(m, 0, -1):

            # * 만약 처음 들어오는 물건이면 w가 해당 물건무게 이상이면 그 물건의 값어치를 모두 넣음
            if i == 0 and arr[i][0] <= w:
                dp[w] = arr[i][1]

            # * 만약 i번째 물건이고, 무게가 i번째 물건의 무게보다 무거우면
            # * i-1번째 까지 사용해서 w무게인 값어치와, i번째를 포함한 값어치를 비교해 높은값을 넣는다.
            elif arr[i][0] <= w:
                dp[w] = max(dp[w], dp[w - arr[i][0]] + arr[i][1])

    print(f'#{tnt} {dp[-1]}')