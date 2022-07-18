T = 10

for tnt in range(1, T+1):
    step = int(input())
    arr = [list(map(int, input().split())) for _ in range(100)]
    result = 0
    
    sum_dl = 0
    sum_dr = 0
    for n in range(100):
        sum_n = 0
        sum_m = 0
        for m in range(100):
            sum_n += arr[n][m]
            sum_m += arr[m][n]
            
            if m == n:
                sum_dl += arr[n][m]
            
            if m + n == 99:
                sum_dr += arr[n][m]
            
        result = max(result, sum_m, sum_n, sum_dl, sum_dr)
        
    print(f'#{tnt} {result}')
    
