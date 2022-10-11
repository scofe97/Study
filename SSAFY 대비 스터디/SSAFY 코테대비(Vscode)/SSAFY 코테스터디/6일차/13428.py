for tnt in range(1, int(input())+1):
    
    # * 초기화값 받음
    arr = list(map(int,list(input())))
    arr_max = arr.copy()
    arr_min = arr.copy()
    size = len(arr_max)
    
    # * 길이가 2이상이면 진행 (1이면 그대로 출력)
    if len(arr) > 1:
        
        # * 최대값 구하는 로직
        # * i번째 idx부터 리스트의 최대값을 찾는다 ( 여기서 중요한건 최대값이 여러개 있는데 뒤에서부터 찾아야함 )
        # * i번째 숫자가 최대값과 다르면 스왑
        for i in range(size):  
            max_value = max(arr_max[i:])

            if arr_max[i] != max_value:    
                # * 뒤에서부터 찾는 식    
                max_idx = (arr_max.reverse().index(max_value) + 1) * -1
                
                    
                # * i번째 숫자가 최대값과 다르면 스왑    
                arr_max[i], arr_max[max_idx] = arr_max[max_idx], arr_max[i]
                break
            
            
        # * 최소값 구하는 로직
        for i in range(size): 
            
            # * idx가 0일때는 0을 넣으면 안되므로 리스트에서 0을 뺀 최소값을 구함
            if i == 0:
                min_value = min(list(set(arr_min[:]) - {0}))

                # * 뒤에서부터 최소값 찾기
                if arr_min[i] != min_value:
                    temt = arr_min.reverse()
                    min_idx = (temt.index(min_value) + 1) *-1

                    
                    # * 스왑
                    arr_min[i], arr_min[min_idx] = arr_min[min_idx], arr_min[i]
                    break
            
            # * 만약 idx가 0이아니면 0을 활용해도 됨
            elif i > 0:
                min_value = min(arr_min[i:])
                
                if arr_min[i] != min_value:
                    min_idx = (arr_min.reverse().index(min_value) + 1) *-1
                    
                    arr_min[i], arr_min[min_idx] = arr_min[min_idx], arr_min[i]
                    break
                
    
    arr_min = ''.join(list(map(str, arr_min)))
    arr_max = ''.join(list(map(str, arr_max)))
    
    print(f'#{tnt} {arr_min} {arr_max}')
    
    
    
    
    
    