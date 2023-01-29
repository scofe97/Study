for tnt in range(1, int(input())+1):
    n, p, t = map(int, input().split())

    max_result = min(p, t)
    min_result = 0 if n - (p+t) >= 0 else (p+t) - n

    print(f'#{tnt} {max_result} {min_result}')