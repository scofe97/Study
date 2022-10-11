result = []

for tnt in range(1, int(input()) + 1):
    arr = list(map(int, input().split()))

    alice = arr[0] / arr[1]
    bob = arr[2] / arr[3]

    if alice > bob:
        result.append(f'#{tnt} ALICE')
    elif alice < bob:
        result.append(f'#{tnt} BOB')
    else:
        result.append(f'#{tnt} DRAW')

# 단순 print 로 하면 시간초과가 나오므로 배열로 받아서 출력해준다
for i in result:
    print(i)
