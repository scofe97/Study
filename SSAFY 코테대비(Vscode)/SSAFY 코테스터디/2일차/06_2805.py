
for tnt in range(1, int(input())+1):
    size = int(input())
    arr = [list(input()) for _ in range(size)]
    result = 0
    
    # * 중앙 값
    point = size//2
    
    for i in range(size):
        line = abs(point-i)
        
        # * 중앙일수록 모든 열을 더한다
        # * 각 행은 현재 i번째 행인경우 (중앙지점 - i 의 절대값) 에서 배열의 크기 - (중앙지점 - i 의 절대값) 범위를 더한다
        for j in range(line, size-line):
            result += int(arr[i][j])
        
    print(f'#{tnt} {result}')