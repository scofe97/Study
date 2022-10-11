for tnt in range(1, int(input())+1):
    n, m = map(int, input().split())

    print(f'#{tnt} {(n+m)%24}')