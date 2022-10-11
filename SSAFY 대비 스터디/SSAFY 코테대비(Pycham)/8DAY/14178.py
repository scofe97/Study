for tnt in range(1, int(input()) + 1):
    n, d = map(int, input().split())

    # d-n ~ d+n 까지 범위를 커버한다면 (d*2+1) 의 범위를 커버한다는 뜻이다
    if n % (d * 2 + 1):
        # 나머지가 발생한다면 하나를 더 설치
        print(f'#{tnt} {(n // (d * 2 + 1)) + 1}')
    else:
        print(f'#{tnt} {(n // (d * 2 + 1))}')
