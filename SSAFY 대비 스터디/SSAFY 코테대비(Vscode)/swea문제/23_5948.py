from math import radians


# 07:53

def problem_5948(cnt, idx, result):
    if cnt == 3:
        sum_arr.add(result)
        return
    
    for i in range(idx, len(arr)):
        problem_5948(cnt+1, i+1, result + arr[i])

for tnt in range(1, int(input())+1):
    arr = list(map(int, input().split()))
    
    sum_arr = set()
    problem_5948(0, 0, 0)
    sum_arr = list(sum_arr)
    sum_arr.sort()
    
    print(f'#{tnt} {sum_arr[-5]}')
    
    