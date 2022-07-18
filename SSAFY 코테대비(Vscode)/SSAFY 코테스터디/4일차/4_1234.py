def problem_1234():
    
    # * arr을 모두 순회하면서 쌍이 나오는지 확인
    for i in range(len(arr)-1):
        
        # * 만약 쌍이나온다면 그 요소를 빼고 다시 순환한다.
        if arr[i] == arr[i+1]:
            arr.pop(i)
            arr.pop(i)
            problem_1234()
            
            break
        

for tnt in range(1, 11):
    # * 초기화 부분
    size, arr = input().split()
    arr = list(arr)
    size = int(size)
    
    # * 순환 실행
    problem_1234()
    
    # * 중복을 제거한 배열 문자열로 출력
    result = ''.join(arr)
    
    print(f'#{tnt} {result}')
    
    
    