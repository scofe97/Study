result = []

for tnt in range(1, int(input())+1):
    num = int(input())

    # n개의 연속된 합
    s1 = (1 + num) * num // 2
    # n개의 연속된 홀수의합 -> (n개의 연속된 합 + n-1개의 연속된 합)
    s2 = s1 + num * (num - 1) // 2
    # n개의 연속된 짝수의 합 -> (n개의 연속된 합 * 2)
    s3 = s1 * 2

    result.append(f'#{tnt} {s1} {s2} {s3}')

for i in result:
    print(i)