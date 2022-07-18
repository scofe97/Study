def problem_3282(idx,value, volume):
    global result

    dp = [0 for i in range(m+1)]
    
    for i in range(1, n+1):  # I번째 아이템
        for w in range(m, 0, -1):  # 뒤에서부터 검색
                                # previous computation when taking i-1 items
            if arr[i-1][0] <= w:
                # 무게가 w일때 기존이 더 가치가있는가, 아니면 i번째를 포함하는게 더 가치있는가
                dp[w] = max(dp[w], dp[w-arr[i-1][0]]+arr[i-1][1])
 
    return dp[m]
 

for tnt in range(1, int(input())+1):
    # * n-> 물건개수, m -> 가방부피
    n, m = map(int,input().split())
    arr = [list(map(int,input().split())) for _ in range(n)]

    result = problem_3282(0,0,0)
    
    print(f'#{tnt} {result}')