
result = []
dp = [0] * 1001

for i in range(1, 1001):

    # 이렇게하면 값이 정수와 동일한지 확인함
    if i ** 0.5 != int(i ** 0.5):
        continue
    s1 = str(i)
    s2 = str(int(i ** 0.5))

    if s1 == s1[::-1] and s2 == s2[::-1]:
        dp[i] = 1

for tnt in range(1, int(input())+1):
    n, m = map(int, input().split())

    arr = dp[n:m+1]
    result.append(f'#{tnt} {arr.count(1)}')

for i in result:
    print(i)
