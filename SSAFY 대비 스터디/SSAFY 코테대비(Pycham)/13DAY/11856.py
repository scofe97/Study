for tnt in range(1, int(input())+1):
    s = input()

    for i in s:
        if s.count(i) != 2:
            print(f'#{tnt} No')
            break
    else:
        print(f'#{tnt} Yes')