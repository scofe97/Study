
# * count 만큼의 빌딩이있고, 빌딩좌우로 2칸씩의 공간이 얼마나 있는지 확인하는 문제
for tnt in range(1, 11):
    count = int(input())
    building = list(map(int,input().split()))
    view = 0
    
    # * 처음과 끝 2개는 비어있으므로 생략하는범위
    for i in range(2,count-2):
        left = 0
        right = 0
        
        # * 좌측 view
        if building[i] - building[i-1] > 0 and building[i] - building[i-2] > 0:
            left = min(building[i] - building[i-1], building[i] - building[i-2])
         # * 우측 view   
        if building[i] - building[i+1] > 0 and building[i] - building[i+2] > 0:
            right = min(building[i] - building[i+1], building[i] - building[i+2])
        
        # * 둘 중 최소값을 구함
        view += min(left, right)
        
    print(f'#{tnt} {view}')
        