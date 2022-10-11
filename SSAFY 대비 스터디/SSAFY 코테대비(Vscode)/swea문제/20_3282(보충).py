def knapSack(W, wt, val, n):
		# 0~50 , 0~n K 2차원배열 생성
    K = [[0 for x in range(W + 1)] for x in range(n + 1)]
 
    # DP를 활용한 방식
    for i in range(n + 1):
        for w in range(W + 1):
						# 개수와 무게가 둘다 0인경우
            if i == 0 or w == 0:
                K[i][w] = 0
            # ex) 1~3번중 1번만 사용 
            # ex) 만약 1번의 무게가 w보이하가 되면 그 값에 넣어줌
            elif wt[i-1] <= w:
                # i번째 아이템 w의 최대값
                # i개 가치 + i-1개에서 무게뺀 최대값, 그냥 i
                K[i][w] = max(val[i-1]
                          + K[i-1][w-wt[i-1]], 
                              K[i-1][w])
                
            # i번째 아이템이 w보다 무거운경우
            else:
                K[i][w] = K[i-1][w]
    
    print(K)
    return K[n][W]
 
 
# Driver code
val = [60, 100, 120]
wt = [10, 20, 30]
W = 50
n = len(val)
print(knapSack(W, wt, val, n))