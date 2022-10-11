
# * 앞뒤로 돌려도 똑같은 문자열 찾기
for tnt in range(1,11):
    size = int(input())
    board = [input() for _ in range(8)]
    result = 0
    
    for n in range(8):
        for m in range(8):
            
            # * 가로의 경우
            if m+size-1 < 8 and board[n][m : m+size] == board[n][m : m+size][::-1]:
                result += 1
            
            # * 세로의 경우
            if n+size-1 < 8:   
                char = ''
                for i in range(n,n+size):
                    char += board[i][m]
                    
                if char == char[::-1]:
                    result += 1
    
    print(f'#{tnt} {result}')