def problem_12004(n):
    for i in range(1, 10):
        for j in range(1, 10):
            if i * j == n:
                return True
    return False


for tnt in range(1, int(input()) + 1):
    num = int(input())

    if problem_12004(num):
        print(f'#{tnt} Yes')
    else:
        print(f'#{tnt} No')
