for tnt in range(1, int(input())+1):
    num = int(input())

    arr = [2,3,5,7,11]
    result = []

    for i in arr:
        step = 0
        while not num % i:
            num = num//i
            step += 1

        result.append(step)

    print('#{} {} {} {} {} {}'.format(tnt, *result))