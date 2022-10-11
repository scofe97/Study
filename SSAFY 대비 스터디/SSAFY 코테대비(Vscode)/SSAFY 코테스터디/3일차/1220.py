def problem_1220():
    global result
    
    # * 보드의 각 지점을 모두 검색
    for x in range(size):
        for y in range(size):
            
            # * 만약 N극이면 ↓ 로 이동하고, 2를 만나지 않으면 밖으로 떨어진다.
            if board[y][x] == 1:
                for y2 in range(y+1, size):                    
                    if board[y2][x] == 2:
                        result += 1
                
                        board[y2][x] = -1
                        board[y][x] = -1
                        break
                    
                    # * -1은 이미 대치되있는 블록이다 
                    elif board[y2][x] == -1:
                        board[y][x] = -1
                        break
                    
            # * 만약 S극이면 ↑ 로 이동하고, 1를 만나지 않으면 밖으로 떨어진다.           
            if board[y][x] == '2':
                for y2 in reversed(range(0,y)):                    
                    if board[y2][x] == 1:
                        result += 1
                        
                        board[y2][x] = -1
                        board[y][x] = -1
                        break
                    
                    # * -1은 이미 대치되있는 블록이다 
                    elif board[y2][x] == -1:
                        board[y][x] = -1
                        break
                        

for tnt in range(1, 11):
    # * 초기화
    size = int(input())
    board = [list(map(int,input().split())) for _ in range(size)]
    
    # * 대치되는 블록의 개수
    result = 0
    
    problem_1220()
    
    print(f'#{tnt} {result}')
    
    