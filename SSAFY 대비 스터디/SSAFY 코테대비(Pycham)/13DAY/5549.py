result = []

for tnt in range(1, int(input())+1):
    num = int(input())

    if num % 2:
        result.append(f'#{tnt} Odd')
    else:
        result.append(f'#{tnt} Even')

for i in result:
    print(i)