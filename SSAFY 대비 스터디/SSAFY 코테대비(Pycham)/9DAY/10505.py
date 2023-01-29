def problem_10505(x):
    if x > arr_avg:
        return False
    else:
        return True


for tnt in range(1, int(input())+1):
    size = int(input())
    arr = list(map(int, input().split()))

    arr_avg = int(sum(arr) / len(arr))
    result = filter(problem_10505, arr)
    result = list(result)

    print(f'#{tnt} {len(result)}')
