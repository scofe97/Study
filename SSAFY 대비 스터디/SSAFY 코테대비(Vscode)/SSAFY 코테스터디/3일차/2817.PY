def problem_2817(idx,sum):
    global result
    
    # * 합이 m을 넘어서면 멈춘다
    if sum > m:
        return
    
    # * 합이 m과 동일하면 +1하고 멈춘다 
    elif sum == m:
        result += 1
        return
    
    # * 순열의 모든 경우의 수의 합을 구한다.
    for i in range(idx,n):
        problem_2817(i+1,sum+arr[i])

        

for tnt in range(1, int(input())+1):
    
    # * 초기화
    n, m = map(int, input().split())
    arr = list(map(int,input().split()))
    
    # * 결과값
    result = 0
    
    problem_2817(0,0)
    
    print(f'#{tnt} {result}')
    