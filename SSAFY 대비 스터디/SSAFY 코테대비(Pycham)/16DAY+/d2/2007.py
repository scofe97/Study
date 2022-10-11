for tnt in range(1, int(input())+1):

    s = list(input())
    result = 0
    for i in range(1,11):
        step = set()
        for j in range(i, 31, i):
            step.add(''.join(s[j-i:j]))

        if len(step) == 1:
            result = i
            break


    print(f'#{tnt} {result}')


    # 25:49