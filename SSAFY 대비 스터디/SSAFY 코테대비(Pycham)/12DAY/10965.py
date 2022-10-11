
result = []

# 소인수 분해를 통해 홀수개가 나오는 소수를 모두 곱하면 답이 나온다
# 숫자범위가 10^7 이므로  10000000 * * 0.5 범위까지 소수를 구한다.
def prime_list(num):

    sieve = [1] * (num+1)
    m = int(num ** 0.5)

    for i in range(2, m+1):
        if sieve[i]:
            for j in range(i+i, num, i):
                sieve[j] = 0

    return [i for i in range(2, num) if sieve[i]]


dp = prime_list(int(10000000 ** 0.5)+1)



for tnt in range(1, int(input())+1):
    n = int(input())
    r = 1

    # 만약 n의 어떤 정수의 제곱근이 아니라면
    if n ** 0.5 != int(n ** 0.5):

        # 소수로 n을 계속나누고 홀수면 곱한다.
        for i in dp:
            cnt = 0
            while n % i == 0:
                n = n // i
                cnt += 1

            if cnt % 2:
                r *= i

            # 만약 n이 1이되거나, n이 i(소수)보다 작아지면 멈춘다.
            if n == 1 or i > n:
                break

        # n이 현재 1보다 큰상태면 n 자체를 곱해준다.
        if n > 1:
            r *= n

    result.append(f'#{tnt} {r}')

for i in result:
    print(i)
