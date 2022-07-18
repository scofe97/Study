import sys
input = sys.stdin.readline

# n * m 크기의 보드 , 4개의 버튼
# 2개의 빈 칸에는 동전이 하나씩 놓여져있고, 위치가 다르다

board_size = list(map(int, input().split()))
board = [input() for _ in range(board_size[0])]
move = [(-1,0),(1,0) ,(0,-1),(0,1)]

def bfs():
    
    coin = []
    count = 0
    out = 0
    queue = []
    for n in range(board_size[0]):
        for m in range(board_size[1]):
            if board[n][m] == 'o':
                coin.append((n,m))
    if len(coin) == 1:
        coin.append(coin[0])
    
    queue.append([coin,0])
    while True:
        if len(queue) == 0:
            print(-1)
            return
        
        data = queue.pop(0)
        coin = data[0]
        count = data[1] + 1
        
        if count > 10:
            print(-1)
            break
        
        for m in move:
            coin_step = coin.copy()
            move_coin1 = (coin_step[0][0] + m[0], coin_step[0][1] + m[1])
            move_coin2 = (coin_step[1][0] + m[0], coin_step[1][1] + m[1])

            if move_coin1[0] in range(board_size[0]) and move_coin1[1] in range(board_size[1]):
                if board[move_coin1[0]][move_coin1[1]] != '#':
                    coin_step[0] = move_coin1
            else:
                out += 1
                
            if move_coin2[0] in range(board_size[0]) and move_coin2[1] in range(board_size[1]):
                if board[move_coin2[0]][move_coin2[1]] != '#':
                    coin_step[1] = move_coin2
            else:
                out += 1
                
            
            if out == 1:
                print(count)
                return
            elif coin_step[0] != move_coin1 and coin_step[1] != move_coin2:
                out = 0
                continue
            else :
                out = 0
                queue.append([coin_step,count])
                
        
bfs()
