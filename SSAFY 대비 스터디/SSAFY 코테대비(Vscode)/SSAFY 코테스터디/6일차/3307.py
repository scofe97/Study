for tnt in range(1, int(input())+1):
    
    # * 입력값들 받음
    size = int(input())
    arr = list(map(int,input().split()))
    result = 0
    
    # * dp를 활용함
    dp = [1]*size
    
    # * i번째 까지의 수에서 가장 긴 수열을 dp[i]에 담는다.
    # * 만약 arr[j] (i 이전 arr)가 arr[i] 보다 이하이고
    # * dp[j] (i 이전 dp)가 dp[i] 보다 낮으면
    # * dp[i] 에 dp[j]+1 값을 넣음
    for i in range(size):
        for j in reversed(range(i)):
            if arr[i] >= arr[j] and dp[i] <= dp[j]:
                dp[i] = dp[j]+1
            

    result = max(dp)
    
    print(f'#{tnt} {result}')
    
    