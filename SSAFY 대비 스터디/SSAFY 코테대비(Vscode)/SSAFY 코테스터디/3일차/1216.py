def problem_1216(length):
    for n in range(100):
            for m in range(100):
                
                # * 가로확인
                if m + length <= 100 and arr[n][m:m+length] == arr[n][m:m+length][::-1]:
                    return True
                
                # * 세로확인
                if n + length <= 100:
                    char = ''
                    for i in range(n,n+length):
                        char += arr[i][m]
                        
                    if char == char[::-1]:
                        return True
                      
    return False
                    

for _ in range(10):
    # * 초기화 부분 
    tnt = int(input())
    arr = [input() for _ in range(100)]
    
    # * 결과값
    result = 0
    
    # * 회문 길이 100 ~ 1 까지 체크해봄
    for length in range(100, 1, -1):
        
        # * 만약 해당 length 길이의 회문이 있다면 반복멈추고 결과값 출력
        if problem_1216(length):
            result = length
            break
        
    print(f'#{tnt} {result}')