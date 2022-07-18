
# * 오셀로 규칙대로 변경하는 함수
def change(turn, n, m, size):
    
    # * 상하좌우, 대각선 4방향
    dy = [-1, 1, 0, 0, -1, -1, 1, 1]
    dx = [0, 0, -1, 1, 1, -1, -1, 1]

    for i in range(8):
        step = 1
        while True:
            
            # * 8방향으로 나아갈때 보드판 밖으로 나가거나, 비어있는 칸이면 멈춤
            if n+(step*dy[i]) < 0 or n+(step*dy[i]) >= size or m+(step*dx[i]) < 0 or m+(step*dx[i]) >= size or board[n+(step*dy[i])][m+(step*dx[i])] == 0:
                break
            
            # * 만약 자신의 색깔을 만난다면
            if board[n+(step*dy[i])][m+(step*dx[i])] == turn:
                
                # * 원래 위치에서 움직인 위치까지의 돌을 모두 자신의 색으로 변경
                for s in range(1, step):
                     board[n+(s*dy[i])][m+(s*dx[i])] = turn
                break
            
            step += 1
                
            


for tnt in range(1, int(input())+1):
    # * 오셀로판의 크기와, 턴의 개수
    n, m = map(int, input().split())
    
    # * 각 턴마다 어떤 위치에 놓이는지
    turns = [list(map(int, input().split())) for _ in range(m)]
    
    # * 보드 초기화 (가운데 4개)
    board = [[0]*n for _ in range(n)]
    point = n//2
    board[point-1][point-1] = 2
    board[point][point-1] = 1
    board[point-1][point] = 1
    board[point][point] = 2
    
    
    # * 각 턴의 정보대로 입력
    for turn in turns:
        
        # * 흑의 경우
        if turn[2] == 1:
            # * 놓은 자리에 흑의값 1
            board[turn[0]-1][turn[1]-1] = 1
            # * 오셀로 규칙대로 주위 변경
            change(1,turn[0]-1, turn[1]-1, n )

        elif turn[2] == 2:
            # * 놓은 자리에 백의값 2
            board[turn[0]-1][turn[1]-1] = 2
            # * 오셀로 규칙대로 주위 변경
            change(2,turn[0]-1, turn[1]-1, n )

    count_1 = 0
    count_2 = 0
    
    for y in range(n):
        for x in range(n):
            if board[y][x] == 1:
                count_1 += 1
            elif board[y][x] == 2:
                count_2 += 1
    
    # * 오셀로판에서 흑과 백의 개수
    print(f'#{tnt} {count_1} {count_2}')