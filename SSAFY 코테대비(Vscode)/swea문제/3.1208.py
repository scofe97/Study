def problem_1208(step, cnt):
    global dump
    max = 0
    min = 1e9
    max_idx = 0
    min_idx = 0
    
    for i in range(len(dump)):
        if max < dump[i]:
            max_idx = i
            max = dump[i]

        if min > dump[i]:
            min_idx = i
            min = dump[i]

    if cnt == count:
        print(f'#{step} {max-min}')
        return

    dump[max_idx], dump[min_idx] = dump[max_idx]-1, dump[min_idx]+1
    problem_1208(step, cnt+1)


for i in range(10):
    count = int(input())
    dump = list(map(int, input().split()))

    problem_1208(i+1, 0)
