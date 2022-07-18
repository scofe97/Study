for tnt in range(1,int(input())+1):
    # * 몇진수인지와, 숫자 입력받음
    n, x = input().split()
    s = 0
    
    # * x의 길이만큼 반복
    for i in range(len(x)):
        
        # * 만약 숫자의 길이가 3이고 8진수이면 0 idx값은
        # * 해당 idx 숫자 * 8^2 이다 
        # * 이런식의 반복으로 10진수로 변환
        s += int(x[i]) * (int(n) ** (len(x)-1-i))
    
    # * 10진수로 변환한 값을 n-1로 나눔
    result = s % (int(n)-1)
    
    print(f'#{tnt} {result}')
