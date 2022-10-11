from unicodedata import name


T = int(input())

def problem_2814(cnt, vertex):
    global result, visited
    
    if cnt == n:
        result = n
        return
    
    for v in range(n):   
        if not visited[v] and vertex == -1:
            visited[v] = 1
            problem_2814(cnt+1, v)
            visited[v] = 0
        
        elif not visited[v] and vertex in arr[v]:
            visited[v] = 1
            problem_2814(cnt+1, v)
            visited[v] = 0
    
    result = max(result, cnt)
    

for tnt in range(1, T+1):
    
    result = 0
    
    n, m = map(int, input().split())
    
    arr = [[] for _ in range(n)]
    visited = [0] * n
    
    for _ in range(m):
        v1,v2 = map(int, input().split())
        arr[v1-1].append(v2-1)
        arr[v2-1].append(v1-1)
    problem_2814(0, -1)
    print(f'#{tnt} {result}')
    