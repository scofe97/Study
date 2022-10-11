def check(st1, st2):
    for i in range(len(st1)):
        if st1[i] == st2[i]:
            continue

        if st1[i] == '*' or st2[i] == '*':
            print(f'#{tnt} Exist')
            return
        else:
            break
    else:
        print(f'#{tnt} Exist')
        return

    print(f'#{tnt} Not exist')


for tnt in range(1, int(input())+1):
    s1 = list(input())
    s2 = s1[::-1]

    check(s1, s2)
