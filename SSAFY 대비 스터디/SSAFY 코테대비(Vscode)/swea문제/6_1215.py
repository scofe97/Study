for tnt in range(1, 11):
    
    # 길이 입력
    p = int(input())   
    # 평면 글자판 크기
    N = 8
    # 글자판
    arr = [list(input()) for _ in range(N)]
    # 회문의 수
    cnt = 0
    
    # * 리스트 정리
    # list[0:5] : 0~4 까지 가져옴
    # list[:5] : ~4 까지 모두 가져옴
    # list[:] : 맨앞부터 맨 뒤까지
    # list[:-5] : 맨뒤에서 5번째부터 다가져오기
    # list[-5: -1] : 맨뒤에서 5번째부터 가져오기 (위하고 같음)
    # list[2:8:3] : 인덱스 2부터 3씩 증가시키면서 인덱스 7까지 가져옴
    # list[::2] : 리스트 전체에서 인덱스 0부터 2씩 증가시키면서 요소를 가져옴
    
    # 가로인 경우
    for i in range(0,N):
        for j in range(0,N-p+1):
            if arr[i][j:j+p] == arr[i][j:j+p][::-1]:
                cnt += 1
               
         
                
    for j in range(0,N):
        for i in range(0,N-p+1):
            char = ''
            for ci in range(i, i+p):
                char += arr[ci][j]
            
            if char == char[::-1]:
                cnt += 1

            
    print(f'#{tnt} {cnt}')

