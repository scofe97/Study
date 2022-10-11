def problem_1225():
    step = 1
    
    while True:
        # * arr 처음요소를 step 만큼 뺀값을 담고 처음요소를 삭제함
        temt = arr[0] - step
        del(arr[0])
        
        # * 만약 temt가 0보다 크면 그 값을 넣고 계속 진행하고 아니면 0을 넣고 멈춘다.
        if temt >0:
            arr.append(temt)
        else:
            arr.append(0)
            break    
        
        # * step이 5보다 커지면 1로 초기화, 아니면 1씩 추가
        if step >= 5:
            step = 1
        else:
            step += 1

for i in range(10):
    tnt = int(input())
    arr = list(map(int, input().split()))
    
    problem_1225()
    
    print(f'#{tnt}' ,end = ' ')
    
    for i in arr:
        print(f'{i}' ,end = ' ')
    print()
    