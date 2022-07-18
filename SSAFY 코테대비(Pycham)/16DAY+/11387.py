result = []

for tnt in range(1, int(input())+1):
    d, l, n = map(int, input().split())

    damage = d

    for i in range(1, n):
        damage += d + i * d//100 * l

    result.append(f'#{tnt} {damage}')


for i in result:
    print(i)