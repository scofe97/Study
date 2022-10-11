for tnt in range(1, int(input())+1):
    n,a,b = map(int, input().split())
    minum = -1

    # n >= i * j 의 사각형을 모두 구함
    for i in range(1, n+1):
        j = 1

        while i*j <= n:
            res = a * abs(i-j) + b * (n-i*j)

            if minum == -1:
                minum = res
            else:
                minum = min(minum, res)
            j += 1

    print(f'#{tnt} {minum}')
