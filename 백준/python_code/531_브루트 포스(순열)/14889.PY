import sys

def dfs(index,step):
    global result
    
    if step == size//2:
        start = 0
        link = 0
        # print(visited)
        for n in range(size):
            for m in range(size):
                if n == m:
                    continue
                
                if visited[n] and visited[m]:
                    # print("start 팀 :",start_link[n][m],n,m)
                    start += start_link[n][m]
                elif  not visited[n] and not visited[m]:
                    # print("link 팀 :",start_link[n][m],n,m)
                    link += start_link[n][m]

        result = min(result, abs(start-link))
        return

    for i in range(index,size):
        if not visited[i]:
            visited[i] = True
            dfs(i+1,step+1)
            visited[i] = False

input = sys.stdin.readline
size = int(input())
# input() : 한 줄의 문자열을 모두 입력
# map() 리스트의 모든 원소에 각각 특정한 함수적용

start_link = [list(map(int,input().split())) for _ in range(size)]
visited = [False for _ in range(size)]
result = 1000000             
dfs(0,0)
print(result)

