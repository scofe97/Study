T = int(input())

def problem_2817(idx,case):
    global result
    
    if sum(case) > k or len(case) == n:
        return
    
    if k == sum(case):
        result += 1
        return
    
    for i in range(idx, n):
        if not visited[i]:
            visited[i] = 1
            case.append(arr[i])
            
            problem_2817(i+1, case)
            
            case.pop()
            visited[i] = 0
            
        

for tnt in range(1, T+1):
    result = 0
    n, k = map(int,input().split())
    visited = [0] * n
    case = []
    arr = list(map(int,input().split()))
    
    problem_2817(0, case)
    
    print(f'#{tnt} {result}')