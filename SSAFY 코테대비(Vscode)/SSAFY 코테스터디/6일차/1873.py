def find_tank():
    for y in range(h):
        for x in range(w):
            if arr[y][x] in ['^', 'v', '<', '>']:
                return x, y

def shot(x,y):
    global arr
    
    if arr[y][x] == '^':
        for i in reversed(range(y)):
            if arr[i][x] == '*':
                arr[i][x] = '.'
                return
    
            elif arr[i][x] == '#':
                return
            
    elif arr[y][x] == 'v':
        for i in range(y+1, h):
            if arr[i][x] == '*':
                arr[i][x] = '.'
                return
    
            elif arr[i][x] == '#':
                return
            
    elif arr[y][x] == '<':
        for i in reversed(range(x)):
            if arr[y][i] == '*':
                arr[y][i] = '.'
                return
    
            elif arr[y][i] == '#':
                return
            
    elif arr[y][x] == '>':
        for i in range(x+1,w):
            if arr[y][i] == '*':
                arr[y][i] = '.'
                return
    
            elif arr[y][i] == '#':
                return



for tnt in range(1, int(input())+1):
    h, w = map(int ,input().split())
    
    arr= [list(input()) for _ in range(h)]
    
    size = int(input())
    op = list(input())
    
    tank_point_x, tank_point_y = find_tank()

    while op:
        
        op_step = op.pop(0)
        
        if op_step == 'S':
            shot(tank_point_x, tank_point_y)
            
        elif op_step == 'U':
            if tank_point_y-1 >= 0 and arr[tank_point_y-1][tank_point_x] == '.':
                arr[tank_point_y][tank_point_x] = '.'
                
                tank_point_y -= 1
                
                arr[tank_point_y][tank_point_x] = '^'
                
            else:
                arr[tank_point_y][tank_point_x] = '^'
                
                
        elif op_step == 'D':
            if tank_point_y+1 < h and arr[tank_point_y+1][tank_point_x] == '.':
                arr[tank_point_y][tank_point_x] = '.'
                
                tank_point_y += 1
                
                arr[tank_point_y][tank_point_x] = 'v'
                
            else:
                arr[tank_point_y][tank_point_x] = 'v'
                
                
        elif op_step == 'L':
            if tank_point_x-1 >= 0 and arr[tank_point_y][tank_point_x-1] == '.':
                arr[tank_point_y][tank_point_x] = '.'
                
                tank_point_x -= 1
                
                arr[tank_point_y][tank_point_x] = '<'
                
            else:
                arr[tank_point_y][tank_point_x] = '<'
                
                
        elif op_step == 'R':
            if tank_point_x+1 < w and arr[tank_point_y][tank_point_x+1] == '.':
                arr[tank_point_y][tank_point_x] = '.'
                
                tank_point_x += 1
                
                arr[tank_point_y][tank_point_x] = '>'
                
            else:
                arr[tank_point_y][tank_point_x] = '>'
                
    print(f'#{tnt}', end=' ')
    for i in range(h):
        result = ''.join(arr[i][:])
        print(result)