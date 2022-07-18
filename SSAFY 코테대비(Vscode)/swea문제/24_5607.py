
# ? 3. a의 n승 구하기 최적화
def pow(a, n):
    
    # * 0승이면 1
    if n==0 : return 1
    
    # * 1승이면 a
    if n==1 : return a
    
    # * n값이 2로 나누어지면 다음과 같이함
    # * ex 2^8 -> (2^4 * 2^4) % p
    if n%2==0 :
        temp = pow(a, (n/2))
        return (temp*temp)% 1234567891
    
    # * n값이 2로 나누어지wl 않으면 다음과 같이함
    # * ex 2^9 -> (2^8 * 2) % p
    temp = pow(a, n-1)
    return (temp*a) % 1234567891


# ? 1. i! % 1234567891 값을 넣어둠
dp = list(range(0, 1000001))
for i in range(3, 1000001):
    dp[i] = (dp[i] * dp[i-1]) % 1234567891


for tnt in range(1, int(input())+1):
    n, r = map(int, input().split())
    
    # ? 2. 입력받고 함수실행
    # * nCr = n! / r! * (n-r)!
    # * (n! / r! * (n-r)!) mod 1234567891
    # * 모듈러 연산은 / 에 대해서는 합칠수가 없음 -> 곱으로 바꿔야함
    
    # * 식을 간단하게 적기위해 다음을 변수로 담음
    # * (n! / r! * (n-r)!) -> a,    p -> 1234567891(소수)
    # * 1/a -> a^-1
    
    # * a^(p-1) mod p = 1 mod p
    # * (양변에 a^-1 을 곱함)
    # * a^(p-2) mod p = a^-1 mod p
    
    # * 즉 a^(p-2) 를 구해서 모듈러를 하면된다.
    
    
    up = dp[n]
    bottom = (dp[r] * dp[n-r]) % 1234567891
    reBottom = pow(bottom, 1234567891-2)
    
    result = (up*reBottom)%1234567891
    
    
    print(f'#{tnt} {result}')