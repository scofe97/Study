def check(row, column, board):
    for n in range(1,board_size):
        if row-n >= 0 and board[row-n][column]:
            return 0
        
        if row-n >= 0 and column-n >= 0 and board[row-n][column-n]:
            return 0
        
        if row-n >= 0 and column+n < board_size and board[row-n][column+n]:
            return 0
    
    return 1
    

def problem_2806(cnt, board):
    global result
    
    # 모든열에 말을 놓았으면
    if cnt == board_size:
        result += 1
        return
    
    # cnt 열에서 어디서 놓을것인가
    for i in range(board_size):
        if not board[cnt][i]:
            board[cnt][i] = 1
            if check(cnt,i,board):
                problem_2806(cnt+1, board)
            board[cnt][i] = 0
              
                

                

T = int(input())
for i in range(1, T+1):
    board_size = int(input())
    board = [[0]* board_size for _ in range(board_size)]
    result = 0
    problem_2806(0, board)
    print(f'#{i} {result}')

        
    
    