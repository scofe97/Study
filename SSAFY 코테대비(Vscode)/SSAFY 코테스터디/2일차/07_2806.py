def problem_2806(cnt):
    global result
    
    # * 만약 모든 행에 놓았다면 결과값 추가
    if cnt == size:
        result += 1
        return
    
    # * cnt 행에 각 지점마다 놓아보고 가능한지 확인한다.
    for i in range(size):
        arr[cnt][i] = 1
        if check(cnt, i):
            problem_2806(cnt+1)
        arr[cnt][i] = 0
        

# * 가능한지 여부에 대한 함수
def check(row, column):
    
    # * 우리는 idx 0 ~ size-1(위 -> 아래) 순으로 진행한다
    # * 그러므로 ↖ ↑ ↗ 방향으로 퀸이 있는지 확인하면 된다.
    for i in range(3):
        for j in range(1, size):
            if row + j * (dy[i]) < 0 or row + j * (dy[i]) >= size or column + j * (dx[i]) < 0 or column + j * (dx[i]) >= size:
                break
            
            if arr[row + (j * (dy[i]))][column + (j * (dx[i]))] == 1:
                return False
    
    return True

        

for tnt in range(1, int(input())+1):
    size = int(input())
    arr = [[0] * size for _ in range(size)]
    result = 0
    
    dx = [-1,0,1]
    dy = [-1,-1,-1]
    
    problem_2806(0)
    print(f'#{tnt} {result}')
    