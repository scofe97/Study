for tnt in range(1, int(input())+1):
    
    # * n은 사각형의 크기, m은 파리채의 크기
    n, m = map(int, input().split())
    
    # * arr은 사각형의 요소를 받아옴
    arr = [list(map(int,input().split())) for _ in range(n)]
    
    # * 가장 많이 잡는경우를 담는 변수
    result = 0
    
    
    # * 사각형이 5*5이고 파리채가 2*2 크기이면 [0~3][0~3]의 지점을 돈다
    for i in range(n - m + 1):
        for j in range(n - m + 1):
            
            # * 해당 좌표에서 2*2 파리채가 가지는 값
            s = 0
            
            # * 파리채의 크기인 2*2의 값을 모두 더한다.
            for k in range(i, i+m):
                for v in range(j, j+m):
                    s+= arr[k][v]
            
            # * 만약 값이 크다면 변경해줌
            result = max(result, s)
    
    print(f'#{tnt} {result}')