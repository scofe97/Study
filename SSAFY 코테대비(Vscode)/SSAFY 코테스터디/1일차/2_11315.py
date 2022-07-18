def check():
    
    for n in range(size):

        for m in range(size):

            # * 세로 연속확인
            if n+4 < size and five_check(1,0,n,m):
                return True
        
            # * 가로 연속확인
            if m+4 < size and five_check(0,1,n,m):
                return True
                
            # * 우측대각선 확인
            if n+4 < size and m+4 < size and five_check(1,1,n,m):
                return True
                
            # * 좌측대각선 확인
            if n+4 < size and m-4 >= 0 and five_check(1,-1,n,m):
                return True
        
    return False


# * 5개가 연속하는지 확인하는 함수
def five_check(dy,dx,n,m):
    for i in range(5):
        if board[n+(dy*i)][m+(dx*i)] != 'o':
            return False
        
    return True



for tnt in range(1, int(input())+1):
    
    # * 오목판의 크기
    size = int(input())
    
    # * 오목판의 돌의 여부
    board = [input() for _ in range(size)]
    
    # * 5개 연속을 확인
    result = check()

    # * 결과값 출력   
    if result:
        print(f'#{tnt} YES')
    else:
        print(f'#{tnt} NO')