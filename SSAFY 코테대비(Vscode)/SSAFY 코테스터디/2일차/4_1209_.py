
# * 100 X 100 보드에서 각 열, 행, 대각선 합중 최대값 구하기
for _ in range(10):
    tnt = int(input())
    arr = [list(map(int, input().split())) for _ in range(100)]
    
    # * 대각선은 2중 FOR문 내내 구하므로 밖에다 선언
    max_diag_left = 0
    max_diag_right = 0
    
    # * 결과 담는 곳
    result = 0
    
    for i in range(100):
        # * i번째 행과 열의 최대값 담을 변수
        max_row = 0
        max_column = 0
        
        for j in range(100):
            max_row += arr[i][j]
            max_column += arr[j][i]
            
            # * -> 대각선은 i와 j 가 같은경우
            if i == j:
                max_diag_left += arr[i][j]
            
            # * <- 대각선은 i와 j의 합이 99인 경우
            if i+j == 99:
                max_diag_right += arr[i][j]
        
        # * 최대값을 넣어줌
        result = max(max_row, max_column, max_diag_left, max_diag_right, result)
        
    print(f'#{tnt} {result}')
            