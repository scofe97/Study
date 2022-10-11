for tnt in range(1, int(input())+1):
    k, c = map(int, input().split())
    arr = list(map(int, input().split()))
    
    arr.sort(reverse=True)

    result = 0
    for i in range(c):
        result += arr[i]
    
    
    print(f'#{tnt} {result}')
