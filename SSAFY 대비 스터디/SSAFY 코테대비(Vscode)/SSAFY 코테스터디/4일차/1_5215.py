
# * idx (range 시작),  s(포만감), c(칼로리)
def problem_5215(idx, s, c):
    global result
    
    # * 만약 지정된 칼로리 넘어서면 멈춘다.
    if c > L:
        return

    # * 칼로리 초과가 아니라면 현재 포만감을 최대값과 비교해서 크면 넣는다.
    result = max(result, s)
    
    # * 순열방식으로 모든 경우를 방문한다.
    for i in range(idx, n):
        problem_5215(i+1, s + arr[i][0], c + arr[i][1])


for tnt in range(1, int(input())+1):
    
    # * 초기화 부분
    n, L = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(n)]
    
    # * 결과값 
    result = 0

    problem_5215(0, 0, 0)

    print(f'#{tnt} {result}')
