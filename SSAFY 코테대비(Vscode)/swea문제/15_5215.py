def problem_5215(idx, cur, satisfaction):
    global max_satisfaction, visited

    for i in range(idx, count):
        if not visited[i] and cur + material[i][1] <= calorie:
            visited[i] = 1
            problem_5215(i+1, cur+material[i][1], satisfaction+material[i][0])
            visited[i] = 0
    
    if max_satisfaction < satisfaction:
        max_satisfaction = satisfaction


for tnt in range(1, int(input())+1):
    count, calorie = map(int, input().split())
    material = [list(map(int, input().split())) for _ in range(count)]
    visited = [0] * count
    max_satisfaction = 0
    problem_5215(0,0,0)

    print(f'#{tnt} {max_satisfaction}')