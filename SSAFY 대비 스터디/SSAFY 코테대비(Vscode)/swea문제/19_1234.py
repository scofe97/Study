def problem_1234():
    global num

    for i in range(len(num)-1):
        if num[i] == num[i+1]:
            del(num[i:i+2])
            problem_1234()
            return

for tnt in range(1,11):
    l, num = input().split()
    l = int(l)
    num = list(num)

    problem_1234()
    print(f'#{tnt}' , end=' ')
    for i in range(len(num)):
        print(num[i], end='')
    print()