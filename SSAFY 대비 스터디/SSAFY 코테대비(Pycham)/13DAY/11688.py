for tnt in range(1, int(input())+1):

    a = b = 1
    arr = input()

    for i in arr:
        if i == 'L':
            b += a
        elif i== 'R':
            a += b

    print(f'#{tnt} {a} {b}')




