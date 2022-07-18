# 막혀있는 칸이 사각형인지 판별하는 함수
def is_rectangle(count):
    
    
    # size * size 크기의 격자판 입력
    size = int(input())
        
    rectangle = [] # 격자판
    is_first = True # 처음 막혀있는 칸을 구함에 쓰임
    first_position = () # 처음 막혀있는 칸의 좌표를 저장하는데 쓰임
    
    block = 0 # 격자판의 총 막혀있는 칸의 개수
    width = 1 # 정사각형의 너비
    height = 1 # 정가각형의 높이
    
    
    # 격자판을 입력받음 ex -> [['###'], ['#.#'], ['#.#']]
    for _ in range(size):
        line = input()
        rectangle.append(line)
       
       
    # 받아온 격자판에서 막혀있는 칸의 개수와, 처음 막혀있는 칸의 좌표를 구함
    for n in range(size): 
        for m in range(size):
            if rectangle[n][m] == '#':
                block += 1
                
                if is_first:
                    is_first = False
                    first_position = (n,m)
                      
    
    # 처음 막힌 칸에서 height가 얼마나 연속되는지 구함             
    for i in range(first_position[0]+1,size):
        if rectangle[i][first_position[1]] == '#':
            height += 1
        else:
            break
        
    # 처음 막힌 칸에서 width가 얼마나 연속되는지 구함                
    for i in range(first_position[1]+1,size):
        if rectangle[first_position[0]][i] == '#':
            width += 1
        else:
            break
    
    
    # width 와 height가 동일하고, 곱이 격자판의 막혀있는 벽의 개수와 같다면
    if width == height and block == width*height:
        
        # 처음 막혀있는 칸의 연속된 사각형에서의 막혀있는 칸의 개수와
        # 격자판 전체의 막혀있는 칸의 개수가 같은지 비교한다.
        for n2 in range(first_position[0], first_position[0]+height):
            for m2 in range(first_position[1], first_position[1]+width):
                
                # 연속된 사각형에서 막혀있지 않는 사각형이 나온다면 no를 출력
                if rectangle[n2][m2] != '#':
                    print(f'#{count} no')
                    return
                
        # 모두 막혀있다면 yes를 출력한다.
        print(f'#{count} yes')
    else:
        # 너비와 높이가 같지않거나, 사각형의 내부의 막힌칸의 개수가 격자판의 총 막혀있는 개수가 같지않다면 no
        print(f'#{count} no')
                    

# -----------------------------------------------------------------------------


# 총 count 만큼의 격자판을 입력받는다.
count = int(input())

for i in range(count):
    is_rectangle(i+1)