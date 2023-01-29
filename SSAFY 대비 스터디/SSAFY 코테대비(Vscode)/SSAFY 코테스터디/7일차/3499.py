import math

for tnt in range(1, int(input())+1):
    
    # * 입력값 받음
    step = int(input())
    step_arr = list(input().split())
    
    # * 중앙지점을 만들기위한 point ( 홀수인경우 올림해주기 위해서 ceil 사용 )
    point = math.ceil(step / 2)
    
    # * 절반식 나눔 ( 단 크기가 홀수이면 앞의요소가 하나더 많다)
    step_foward = step_arr[:point]
    step_back = step_arr[point:]
    
    result = []
    for i in range(step):

        # * 앞 뒤 배열을 번갈아가며 하나씩 넣음
        if i % 2 == 0:
            result.append(step_foward.pop(0))
        else:
            result.append(step_back.pop(0))
    
    result = " ".join(result)
    
    print(f'#{tnt} {result}')