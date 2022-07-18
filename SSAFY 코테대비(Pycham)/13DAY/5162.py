result = []

for tnt in range(1, int(input())+1):
    b1, b2, m = map(int, input().split())

    result.append(f'#{tnt} {m // min(b1,b2)}')

for i in result:
    print(i)