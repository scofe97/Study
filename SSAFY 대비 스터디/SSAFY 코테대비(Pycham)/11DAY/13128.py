result = []

for tnt in range(1, int(input())+1):
    num = int(input())

    result.append(f'#{tnt} {num//3}')

for i in result:
    print(i)