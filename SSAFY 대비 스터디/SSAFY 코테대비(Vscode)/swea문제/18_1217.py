def problem_1217(cnt):
    global result

    if cnt == m:
        return
    
    result *= n
    problem_1217(cnt+1)

for i in range(10):
    tnt = int(input())
    n, m = map(int, input().split())
    result = 1
    problem_1217(0)

    print(f'#{tnt} {result}')
