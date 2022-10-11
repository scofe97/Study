table = [20, 40, 60, 80, 100, 120, 140, 160, 180, 200]
value = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]

dp = [ [0] * 201 for _ in range(201)]

for i in range(201):
    for j in range(201):
        dp[i][j] = (((i ** 2) + (j ** 2))) ** 0.5

for tnt in range(1, int(input())+1):

    count = int(input())
    result = 0
    
    for i in range(count):
        x, y = map(int, input().split())
        point = dp[abs(x)][abs(y)]
        
        for i, v in zip(table, value):
            if point <= i:
                result += v
                break

    print(f'#{tnt} {result}')
