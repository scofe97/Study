
# * 단조확인 함수
def check(num):
    
    # * 먼저 곱한값을 문자열 리스트로 바꿔주고 각 요소를 정수로 바꿔준다.
    check = list(map(int,list(str(num))))
    
    # * 복사를해서 정렬을 시켜줌
    test = check.copy()
    test.sort()
    
    # * 만약 단조이면 정렬시켜준 배열과, 기존의 배열의 요소가 모두 같아야함
    for i1, i2 in zip(check, test):
        if i1 != i2:
            return False
        
    return True

for tnt in range(1, int(input())+1):
    
    # * 입력값을 받음
    number = int(input())
    arr = list(map(int, input().split()))
    size = len(arr)
    
    # * 단조의 수중 큰값을 뽑는것이므로 큰수부터 곱하기위해 내림차순 정렬을함
    arr.sort(reverse=True)
    
    # * 배열의 크기가 1인경우, 밑의 FOR문이 동작안하므로 값을 미리 담아둠
    result = arr[0]
    
    # * 이제 i번째 요소와 이외의 요소를 곱하며 단조인지 확인한다.
    for i in range(size-1):
        for j in range(i+1, size):
            if arr[i] * arr[j] > result and check(arr[i] * arr[j]):
                result = arr[i] * arr[j]
    
    if check(result):
        print(f'#{tnt} {result}')
    else:
        print(f'#{tnt} -1')
        
                
                
                
                
    
    