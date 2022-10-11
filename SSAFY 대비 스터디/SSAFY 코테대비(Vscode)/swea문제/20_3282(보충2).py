def knapSack(W, wt, val, n):
    dp = [0 for i in range(W+1)]  # DP 배열
 
    for i in range(1, n+1):  # I번째 아이템
        for w in range(W, 0, -1):  # 뒤에서부터 검색
                                # previous computation when taking i-1 items
            if wt[i-1] <= w:
                # 무게가 w일때 기존이 더 가치가있는가, 아니면 i번째를 포함하는게 더 가치있는가
                dp[w] = max(dp[w], dp[w-wt[i-1]]+val[i-1])
 
    return dp[W]
 
 
# Driver code
val = [60, 100, 120]
wt = [10, 20, 30]
W = 50
n = len(val)
# This code is contributed by Suyash Saxena
print(knapSack(W, wt, val, n))