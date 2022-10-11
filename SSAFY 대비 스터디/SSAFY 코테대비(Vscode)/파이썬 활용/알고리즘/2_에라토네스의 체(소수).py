def prime_list(n):
    
    # * 에라토네스의 체 초기화 : n개 요소만큼 True 설정( True 값이면 소수이다. )
    sieve = [True] * n
    
    # * n의 최대 약수는 sqrt(n) 이하이므로 i = sqrt(n) 까지 검사
    
    # * 예를들면 100의 경우 10을 넘어선 숫자는 판별하지 않아도됨
    # * 10을 넘어선 약수는 20 * 5 와 같이 앞에서 나온 5와 순서만 뒤집힌 경우기 때문
    m = int(n ** 0.5)
    
    # * 2~m 까지 범위
    for i in range(2, m+1):
        
        if sieve[i] == True:
            # * i를 제외한 i의 배수들을 모두 False 처리
            for j in range(i+i, n, i):
                sieve[j] = False
                
    # * 소수 목록 산출
    return [i for i in range(2, n) if sieve[i] == True]


print(prime_list(120)) 
# * [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113]