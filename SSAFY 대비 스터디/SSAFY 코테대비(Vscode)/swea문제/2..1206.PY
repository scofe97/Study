def view(count):
    
    building_count = int(input())
    building = list(map(int, input().split()))
    # 맵 함수의 반환값은 map 객체이므로 이를 list 나 tuple로 변환해주어야함
    
    view = 0
    
    for i in range(2, building_count-2):
        left = 0
        right = 0
        
        if building[i] > building[i-1] and building[i] > building[i-2]:
            left = min(building[i] - building[i-1],building[i] - building[i-2])      
        
        if building[i] > building[i+1] and building[i] > building[i+2]:
            right = min(building[i] - building[i+1],building[i] - building[i+2])
        
        view += min(left, right) 
                  
    print(f'#{count} {view}')

for i in range(10):
    view(i+1)
