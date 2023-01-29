for tnt in range(1, int(input())+1):
    n, k = map(int, input().split())
    
    
    # * 전체학생
    result = set(map(str,range(1, n+1)))
    
    # * 과제를 제출한 학생
    arr = set(input().split())
    
    # * 전체 학생 - 과제를 제출한 학생 = 과제를 제출하지 않은 학생
    result = list(result - arr)
    result = ' '.join(result)
    
    print(f'#{tnt} {result}')
    
    