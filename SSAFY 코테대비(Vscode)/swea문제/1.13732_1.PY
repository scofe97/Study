def is_rectangle():
    size = int(input())
        
    rectangle = []
    for _ in range(size):
        line = input()
        rectangle.append(line)
        
    start = ()
    end = ()
    
    stop = False
    
    for s1 in range(size): 
        for s2 in range(size):
            if rectangle[s1][s2] == '#':
                start = (s1,s2)
                stop = True
                break  
        if stop:
            stop = False
            break
        
    for e1 in range(size-1, 0 , -1):
        for e2 in range(size-1, 0 , -1):
            if rectangle[e1][e2] == '#':
                end = (e1,e2)
                stop = True
                break
            
        if stop:
            stop = False
            break
        
        
    for row in range(start[0],end[0]+1):
        for column in range(start[1], end[1]+1):
            if rectangle[row][column] != '#':
                print("no")
                return
            
    print('yes')
    return

if __name__ == '__main__':
    count = int(input())

    for _ in range(count):
        is_rectangle()