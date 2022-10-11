for tnt in range(1, int(input())+1):
    
    # * 초기화 부분
    point1, point2 = map(int, input().split())
    point1_x, point1_y = 0, 0
    point2_x, point2_y = 0, 0


    # * 각 대각선의 가장왼쪽 정수를 dp에 저장해둔다.
    dp = [0, 1]
    step = 1
    
    # * 범위는 각 좌표가 1000이고, 삼각형 모양이므로 1000*1000/2 값까지 구함
    # * 1, 2, 4, 7 ... 계속해서 1, 2, 3 씩 증가하는 패턴을 보임
    while dp[-1] <= 1000*1000/2:
        dp.append(dp[-1] + step)
        step += 1


    # * point들이 어떤 대각선에 있는지 찾는 알고리즘
    for i in range(1, len(dp)):
        
        
        if point1 >= dp[i] and point1 < dp[i+1]:
            point1_x = abs(point1-dp[i]) + 1
            point1_y = i-point1_x+1


        if point2 >= dp[i] and point2 < dp[i+1]:
            point2_x = abs(point2-dp[i]) + 1
            point2_y = i-point2_x+1

    x = point1_x + point2_x
    y = point1_y + point2_y
    
    print(f'#{tnt} {dp[x+y-1] + x - 1}')
