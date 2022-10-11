for tnt in range(1, 11):
    arr_size = int(input())
    arr = list(input().split())
    op_size = int(input())
    op = list(input().split())
    
    
    while op:
        
        temt = op.pop(0)
        
        # * x번의 다음위치부터 y개의 숫자를 차례대로 넣는다.
        if temt == 'I':
            
            x = int(op.pop(0))
            y = int(op.pop(0))
            
            for j in range(y):   
                s = op.pop(0)
                arr.insert(x+j,s)
        
        # * x번의 다음위치부터 y개의 숫자를 지운다 
        elif temt == 'D':
            
            x = int(op.pop(0))
            y = int(op.pop(0))
            
            for _ in range(y):      
                arr.pop(x)
        
        # * 끝에 y번 s를 추가한다       
        elif temt == 'A':
            
            y = int(op.pop(0))
            s = op.pop(0)
            
            for _ in range(y):      
                arr.append(s)
                

                
    print(f'#{tnt}', end = ' ')
    for i in range(10):
        print(f'{arr[i]}', end = ' ')
    print()
