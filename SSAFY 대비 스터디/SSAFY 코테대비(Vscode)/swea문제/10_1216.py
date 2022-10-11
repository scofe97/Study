def problem_1216(length):
    global result 
    if length == 100:
        result = length
        return

    
    for n in range(100):
        for m in range(100-length+1):
            if arr[n][m:m+length] == arr[n][m:m+length][::-1]:
                result = length
                problem_1216(length+1)
                return
    
    for m in range(100):
        for n in range(100-length+1):
            c = ''

            for ci in range(n,n+length):
                c += arr[ci][m]
            
            if c == c[::-1]:
                result = length
                problem_1216(length+1)
                return

                


for _ in range(10):
    result = 2
    tnt = int(input())
    arr = [input() for _ in range(100)]
    problem_1216(3)
    print(f'#{tnt} {result}')
    