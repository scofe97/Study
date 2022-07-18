for tnt in range(1, int(input())+1):
    n1, n2 = map(int, input().split())

    s1_arr = list(input().split())
    s2_arr = list(input().split())

    s1_arr.extend(s2_arr)
    s1_arr = set(s1_arr)
    result = n1+n2 - len(s1_arr)

    print(f'#{tnt} {result}')