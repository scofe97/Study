for tnt in range(1, int(input())+1):
    # * 입력값 받음
    n, q = map(int, input().split())
    arr = [list(map(int,input().split())) for _ in range(q)]
    result = [0] * n
    
    # * 만약 i번째 숫자가 j[0] ~ j[1] 범위에 들어가면 값을 넣어줌
    for i in range(len(arr)):
        for j in range(arr[i][0]-1, arr[i][1]):
            result[j] = i+1
    
    result = ' '.join(list(map(str, result)))
    print(f'#{tnt} {result}')
    
    