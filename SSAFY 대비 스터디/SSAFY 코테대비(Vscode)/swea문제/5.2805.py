def problem_2805(step):
    size = int(input())
    farm = []
    sum = 0
    
    for _ in range(size):
        line = list(input())
        farm.append(line)
        
    center = int(size/2)
    
    for row in range(size):
        for column in range(abs(center-row),size - abs(center-row) ):
            sum += int(farm[row][column])
    
    print(f'#{step} {sum}')

for i in range(1, int(input())+1):
    problem_2805(i)