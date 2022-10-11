def is_rectangle():
    size = int(input())
        
    rectangle = []
    is_first = True
    
    for _ in range(size):
        line = input()
        rectangle.append(line)
       
    for n in range(size): 
        for m in range(size):
            if rectangle[n][m] == '#':
                width = 0
                height = 0
          
                for i in range(n,size):
                    if rectangle[i][m] == '#':
                        height += 1
                    else:
                        break
                                
                for i in range(m,size):
                    if rectangle[n][i] == '#':
                        width += 1
                    else:
                        break
    
       
                if width == height:
                    for n2 in range(n, n+height):
                        for m2 in range(m, m+width):
                            if rectangle[n2][m2] != '#':
                                print('no 모두 #이 아님')
                                return
                    print('yes')
                    return
    
    print('no 정사각형 못만듬')
                    


count = int(input())

for _ in range(count):
    is_rectangle()