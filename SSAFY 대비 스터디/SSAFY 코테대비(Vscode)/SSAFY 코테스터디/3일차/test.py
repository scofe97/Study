def solution(_n, _k, _arr):
    global count
    
    
    print(_n,_k,_arr)
    
    if _n == 1:
        # 처음 인덱스값이 합과 같으면 카운트
        if _arr[0] == _k:
            print("clear 1")
            count += 1
        return
    
    
    for i in range(_n):
        # 바로 같다면 +1
        if _arr[i] == _k:
            print("clear 2")
            count += 1  # 해당 수가 남은 수 k와 같을 때
            
            
        if _arr[i] < _k:
            solution(_n - (i+1), _k - _arr[i], _arr[i + 1:])


for t in range(int(input())):
    n, k = map(int, input().split())
    n_list = list(map(int, input().split()))
    
    count = 0
    solution(n, k, n_list)
    
    print("#" + str(t + 1), count)
    
    
