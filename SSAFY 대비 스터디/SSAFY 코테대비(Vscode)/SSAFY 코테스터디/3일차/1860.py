def problem_1860(tnt):
    for i in range(len(arr)):
        
        # * m, k 가 2 2 이고,  기다리는 사람 arr[i]가 3이라 가정하자
        # * 3초에 오는경우 붕어빵은 2초마다 2개이므로 이 시점에 2개가 있다
        # * 그리고 이 손님은 가장 빨리 오므로 1개 이상(i+1) 이 있어야하므로 가능하다
        if arr[i] // m * k < i+1:
            print(f'#{tnt} Impossible')
            return
        
    print(f'#{tnt} Possible')

for tnt in range(1, int(input())+1):
    # * 초기화
    n,m,k = map(int, input().split())
    arr = list(map(int, input().split()))
    
    # * 기다리는 인원 정렬 ( 오름차순 )
    arr.sort()
    
    problem_1860(tnt)
    