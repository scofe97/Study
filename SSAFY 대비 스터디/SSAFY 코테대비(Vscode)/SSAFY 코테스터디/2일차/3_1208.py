
# * change 만큼의 최대값, 최소값 +- 하는 문제
for tnt in range(1, 11):
    change = int(input())
    arr = list(map(int,input().split()))
    
    for i in range(change):
        # * 리스트의 최대값, 최소값 구함
        arr_min = min(arr) 
        arr_max = max(arr)
        
        # * 최소값은 +1, 최대값은 -1
        arr[arr.index(arr_min)] += 1
        arr[arr.index(arr_max)] -= 1
        
    print(f'#{tnt} {abs(arr_max-arr_min)}')
