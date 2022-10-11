
# * cnt ->몇개의 정점을 방문했는가?
# * vertex -> 현재 위치한 정점
def problem_2814(cnt, vertex):
    
    global result
    
    # * 모두 정점을 방분했다면 멈춘다.
    if cnt == v:
        result = v
        return
    
    # * 
    for i in range(v):
        # * vertex -1 -> 아직 방문한 정점이 없는 상황
        if not visited[i] and vertex == -1:
            visited[i] = 1
            problem_2814(cnt+1, i)
            visited[i] = 0
        
        # * 방문한 정점이 있는 경우는 graph에서 방문하지 않은 정점이 있나 확인
        elif not visited[i] and vertex in graph[i]:
            visited[i] = 1
            problem_2814(cnt+1, i)
            visited[i] = 0
            
    result = max(result, cnt)   
        
    

for tnt in range(1, int(input())+1):
    
    # * 초기화
    v, e = map(int, input().split())
    graph = [[] for _ in range(v)]
    visited = [0] * v
    
    # * 간선에 방향성이 없으므로 2,3 의 간선인 경우 2->3,  3->2 모두 만족함
    for e in range(e):
        v1,v2 = map(int,input().split())
        graph[v1-1].append(v2-1)
        graph[v2-1].append(v1-1)
    
    # * 결과값 
    result = 0    
    
    problem_2814(0,-1)
    
    print(f'#{tnt} {result}')
