result = []

for tnt in range(1, int(input())+1):
    n1, n2, m1, m2 = map(int,input().split())

    # 해당 범위가 겹치는 부분이다.
    r = min(n2, m2) - max(n1, m1)

    if r >= 0:
        result.append(f'#{tnt} {r}')
    else:
        result.append(f'#{tnt} 0')

for i in result:
    print(i)
