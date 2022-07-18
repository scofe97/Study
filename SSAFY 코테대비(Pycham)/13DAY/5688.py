dp =[0] * 1000001
for i in range(1000001):
    dp[i] = i ** 3

for tnt in range(1, int(input())+1):
    num = int(input())

    if num in dp:
        print(f'#{tnt} {dp.index(num)}')
    else:
        print(f'#{tnt} -1')